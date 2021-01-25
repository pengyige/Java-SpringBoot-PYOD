package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.DictCodeEnum;
import top.yigege.constant.OrderStatusEnum;
import top.yigege.constant.ProductTypeEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.exception.BusinessException;
import top.yigege.model.Coupon;
import top.yigege.model.CouponActivity;
import top.yigege.model.Product;
import top.yigege.dao.ProductMapper;
import top.yigege.model.PurchaseHistory;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.service.ICouponActivityProductService;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponService;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.service.IPurchaseHistoryService;
import top.yigege.service.ISysDictService;
import top.yigege.service.ISysUserService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;
import top.yigege.service.IUserVipCardService;
import top.yigege.util.WeixinUtil;
import top.yigege.vo.wx.WxPayInfoBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    ProductMapper productMapper;

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    ICouponActivityProductService iCouponActivityProductService;

    @Autowired
    IPurchaseHistoryService iPurchaseHistoryService;

    @Autowired
    IGenerateIDService iGenerateIDService;

    @Autowired
    WeixinUtil weixinUtil;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ICouponService iCouponService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Autowired
    IUserVipCardService iUserVipCardService;

    @Autowired
    IUserService iUserService;

    @Autowired
    ISysDictService iSysDictService;

    @Autowired
    ISysUserService iSysUserService;

    @Override
    public List<Product> queryAllProductInfo(Long merchantId) {

        List<Product> productList = new ArrayList<>();
        //判断当前购买送礼活动是否有开启
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(merchantId,ActivityTypeEnum.BUY_PRODUCT);
        if (null != couponActivity) {
            productList = queryProductWithCoupon(couponActivity.getCouponActivityId());
        }else {
            LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
            productLambdaQueryWrapper.eq(Product::getMerchantId,merchantId);
            productList = list(productLambdaQueryWrapper);
        }
        return productList;
    }

    @Override
    public List<Product> queryProductWithCoupon(Long couponActivityId) {
        return productMapper.queryProductWithCoupon(couponActivityId);
    }

    @Override
    public Product queryProductDetailWithCoupon(Long couponActivityId,Long productId){
        return  productMapper.queryProductDetailWithCoupon(couponActivityId,productId);
    }




    @Override
    public WxPayInfoBean buy(Long productId,Long userId,Long vipCardId) {
        Product product = getById(productId);
        if (null == product) {
            throw new BusinessException(ResultCodeEnum.NO_PRODUCT);
        }

        User user = userService.getById(userId);
        if (null == user) {
            throw  new BusinessException(ResultCodeEnum.NO_USER);
        }


        WxPayInfoBean wxPayInfoBean = new WxPayInfoBean();

        try {
            String orderNo = iGenerateIDService.getNo(BusinessFlagEnum.PRODUCT.getMsg());

            wxPayInfoBean = weixinUtil.getPrePayInfo(
                    iSysUserService.queryWxConfigByMerchantId(user.getMerchantId()),
                    product.getName(),
                    orderNo,
                    product.getPrice(),
                    user.getOpenid());


            //生成系统订单记录
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setOrderNo(orderNo);
            purchaseHistory.setProductId(productId);
            purchaseHistory.setUserId(userId);
            purchaseHistory.setVipCardId(vipCardId);
            purchaseHistory.setOrderStatus(OrderStatusEnum.ALREADY_ORDERS.getCode());
            iPurchaseHistoryService.save(purchaseHistory);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(ResultCodeEnum.ORDERS_FAIL);
        }

        return wxPayInfoBean;
    }

    @Transactional
    @Override
    public void buyProductFinishHanlder(String orderNo) {
        PurchaseHistory purchaseHistory = iPurchaseHistoryService.queryPurchaseDetail(orderNo);
        //更新状态，发放奖励
        purchaseHistory.setOrderStatus(OrderStatusEnum.ALREADY_PAY.getCode());
        iPurchaseHistoryService.updateById(purchaseHistory);

        Product product = getById(purchaseHistory.getProductId());

        if (ProductTypeEnum.BUG_TICKET.getCode().equals(product.getProductType())) {
            //超值券包会累积豆豆
            Double getPea = (product.getPrice()/100.0d) / Double.parseDouble(iSysDictService.getValue(DictCodeEnum.SPEND_MONEY_GET_COIN.getCode()));
            userService.addPea(purchaseHistory.getUserId(),getPea);
        }else {
            iUserVipCardService.recharge(purchaseHistory.getUserId(),purchaseHistory.getVipCardId(),product.getPrice()/100.0d);
        }

        //判断当前购买送礼活动是否有开启
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(product.getMerchantId(),ActivityTypeEnum.BUY_PRODUCT);
        if (null != couponActivity) {
            product = queryProductDetailWithCoupon(couponActivity.getCouponActivityId(),product.getProductId());
            //赠送商品对应的优惠券福利
            if (null != product) {
                List<Coupon> couponList = product.getCouponList();
                Date pickDate = new Date();
                List<UserCoupon> userCouponList = couponList.stream().map(item ->{
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setUserId(purchaseHistory.getUserId());
                    userCoupon.setVipCardId(purchaseHistory.getVipCardId());
                    userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                    userCoupon.setCouponId(item.getCouponId());
                    userCoupon.setNum(item.getNum());
                    userCoupon.setExpireTime(iCouponService.queryExpireDate(item.getCouponId(),pickDate));
                    userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                    return userCoupon;
                }).collect(Collectors.toList());
                //保存
                iUserCouponService.batchAddUserCoupon(userCouponList);
            }
        }


    }
}
