package top.yigege.controller.api.coupon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.userVipCard.BindUserVipCardDTO;
import top.yigege.model.CouponActivity;
import top.yigege.service.ICouponService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserVipCardService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ApiVipCardController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月13日 21:27
 */
@RestController
@RequestMapping("/api/coupon")
@Validated
@Api(tags = "API-优惠券相关请求")
public class ApiCouponController {

    @Autowired
    ICouponService iCouponService;

    @Autowired
    IUserCouponService iUserCouponService;


    @ApiOperation("查询优惠券详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券状态id", required = true, dataType = "int")
    })
    public ResultBean queryCouponDetail(@NotNull(message = "优惠券id不能为空") Long couponId){
        return ApiResultUtil.success(iCouponService.getById(couponId));
    }

    @ApiOperation("查询用户优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "couponStatus", value = "优惠券状态(1-可使用,2-已使用,5-已过期)", required = true, dataType = "int")
    })
    @PostMapping("/queryUserCouponList")
    public ResultBean queryUserCouponList(@NotNull(message = "优惠券状态不能为空") Integer couponStatus, @RequestAttribute Long userId){
        return ApiResultUtil.success(iUserCouponService.queryUserCouponList(couponStatus,userId));
    }

    @ApiOperation("赠送优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userCouponId", value = "用户优惠券ID", required = true, dataType = "int")
    })
    @PostMapping("/giveCoupon")
    public ResultBean giveCoupon(@NotNull(message = "用户优惠券id不能为空") Long userCouponId) {
        return ApiResultUtil.success(iUserCouponService.giveCoupon(userCouponId));
    }

    @ApiOperation("领取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userCouponId", value = "用户优惠券ID", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "couponActivityId", value = "优惠券活动id", required = true, dataType = "int")

    })
    @PostMapping("/getCoupon")
    public ResultBean getCoupon(@NotNull(message = "用户优惠券id不能为空") Long userCouponId
            ,@NotNull(message = "活动id不能为空") Long couponActivityId
            ,@RequestAttribute Long userId) {
        iUserCouponService.getCoupon(userCouponId,couponActivityId,userId);
        return ApiResultUtil.success();
    }


    @ApiOperation("兑换CDkey")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "cdkey", value = "cdkey", required = true, dataType = "string")
    })
    @PostMapping("/exchangeCDKey")
    public ResultBean exchangeCDKey(@NotNull(message = "兑换码不能为空") String cdkey,@RequestAttribute Long userId) {
        iUserCouponService.exchangeCDkey(cdkey,userId);
        return ApiResultUtil.success();
    }


}
