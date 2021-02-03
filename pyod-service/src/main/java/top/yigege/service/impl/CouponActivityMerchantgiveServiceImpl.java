package top.yigege.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.RangeTypeEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.AddCouponActivityMerchantgiveDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityMerchantgivePageListDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityIntegral;
import top.yigege.model.CouponActivityMerchantgive;
import top.yigege.dao.CouponActivityMerchantgiveMapper;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.service.ICouponActivityMerchantgiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商家发券 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-30
 */
@Service
public class CouponActivityMerchantgiveServiceImpl extends ServiceImpl<CouponActivityMerchantgiveMapper, CouponActivityMerchantgive> implements ICouponActivityMerchantgiveService {

    @Resource
    CouponActivityMerchantgiveMapper couponActivityMerchantgiveMapper;

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    IUserService iUserService;

    @Autowired
    ICouponService iCouponService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Transactional
    @Override
    public void addCouponActivityMerchantgive(AddCouponActivityMerchantgiveDTO addCouponActivityMerchantgiveDTO) {
        if (null == addCouponActivityMerchantgiveDTO.getType()) {
            throw new BusinessException(ResultCodeEnum.ERROR);
        }

        CouponActivity couponActivity = iCouponActivityService.getById(addCouponActivityMerchantgiveDTO.getCouponActivityId());
        Date expireDate = iCouponService.queryExpireDate(addCouponActivityMerchantgiveDTO.getCouponId(), new Date());
        if (addCouponActivityMerchantgiveDTO.getType().equals(RangeTypeEnum.ALL.getCode())) {
            List<User> userList = iUserService.queryUserByMerchantId(couponActivity.getMerchantId());
            if (!userList.isEmpty()) {
                List<UserCoupon> userCouponList = new ArrayList<>();
                for (User user : userList) {
                    for (int i = 0; i < addCouponActivityMerchantgiveDTO.getNum(); i++) {
                        UserCoupon userCoupon = new UserCoupon();
                        userCoupon.setUserId(user.getUserId());
                        userCoupon.setVipCardId(user.getVipCardId());
                        userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                        userCoupon.setCouponId(addCouponActivityMerchantgiveDTO.getCouponId());
                        userCoupon.setExpireTime(expireDate);
                        userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                        userCouponList.add(userCoupon);
                    }
                }

                addCouponActivityMerchantgiveDTO.setUserNum(userList.size());
                //保存
                iUserCouponService.batchAddUserCoupon(userCouponList);
            }else {
                throw new BusinessException(ResultCodeEnum.NO_USER);
            }
        }else {
            User uesr = iUserService.queryUserByMobile(couponActivity.getMerchantId(),addCouponActivityMerchantgiveDTO.getMobile());
            if (null == uesr) {
                throw new BusinessException(ResultCodeEnum.NO_USER);
            }else {
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setUserId(uesr.getUserId());
                userCoupon.setVipCardId(uesr.getVipCardId());
                userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                userCoupon.setCouponId(addCouponActivityMerchantgiveDTO.getCouponId());
                userCoupon.setExpireTime(expireDate);
                userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());

                addCouponActivityMerchantgiveDTO.setUserNum(1);
                iUserCouponService.save(userCoupon);
            }
        }

        CouponActivityMerchantgive couponActivityMerchantgive = new CouponActivityMerchantgive();
        BeanUtil.copyProperties(addCouponActivityMerchantgiveDTO,couponActivityMerchantgive);
        save(couponActivityMerchantgive);

    }

    @Override
    public PageBean queryCouponActivityMerchantgivePageList(QueryCouponActivityMerchantgivePageListDTO queryCouponActivityMerchantgivePageListDTO) {
        Page page = new Page(queryCouponActivityMerchantgivePageListDTO.getPage(),
                queryCouponActivityMerchantgivePageListDTO.getPageSize());
        List<CouponActivityMerchantgive> couponActivityMerchantgiveList = couponActivityMerchantgiveMapper.queryCouponActivityMerchantgivePageList(
                queryCouponActivityMerchantgivePageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityMerchantgiveList);
    }
}
