package top.yigege.controller.api.product;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.model.CouponActivity;
import top.yigege.model.Product;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.IProductService;
import top.yigege.service.ISysUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.WeixinUtil;
import top.yigege.vo.ResultBean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Api(tags = "API-商品模块")
@RestController
@RequestMapping("/api/product")
public class ApiProductController {

    @Autowired
    WeixinUtil weixinUtil;

    @Autowired
    IProductService iProductService;

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ICouponActivityService iCouponActivityService;

    @ApiOperation("购买")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "productId", value = "商品id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "vipCardId", value = "会员卡id", required = true, dataType = "int")
    })
    @PostMapping("/buy")
    public ResultBean buy(@NotNull(message = "商品id不能为空") Long productId,
                         @NotNull(message = "会员卡不能为空") Long vipCardId,
                          @RequestAttribute Long userId) {
        return ApiResultUtil.success(iProductService.buy(productId,userId,vipCardId));
    }

    @ApiOperation("查询商品订单支付状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderNo", value = "订单编号", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "merchantId", value = "商户id", required = true, dataType = "string")
    })
    @PostMapping("/queryOrderStatus")
    public ResultBean queryOrderStatus(@NotNull(message = "商户id不能为空") Long merchantId,
                                       @NotBlank(message = "订单编号不能为空") String orderNo) throws Exception {
        return ApiResultUtil.success(weixinUtil.queryOrderStatus(iSysUserService.queryWxConfigByMerchantId(merchantId),orderNo));
    }

    @ApiOperation("查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "productId", value = "商品id", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "merchantId", value = "商户id", required = true, dataType = "string")
    })
    @PostMapping("/queryProductDetail")
    public ResultBean queryProductDetail(@NotNull(message = "商品id不能为空") Long productId,
                                         @NotNull(message = "商户id不能为空") Long merchantId) {
        //判断当前购买送礼活动是否有开启
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(merchantId, ActivityTypeEnum.BUY_PRODUCT);
        if (null != couponActivity) {
           return ApiResultUtil.success(iProductService.queryProductDetailWithCoupon(couponActivity.getCouponActivityId(),
                    productId));
        }else {
            return  ApiResultUtil.success(iProductService.getById(productId));
        }
    }
}
