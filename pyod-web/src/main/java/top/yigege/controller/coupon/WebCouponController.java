package top.yigege.controller.coupon;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.AddCouponDTO;
import top.yigege.dto.modules.coupon.ModifyCouponDTO;
import top.yigege.dto.modules.coupon.QueryCouponPageListDTO;
import top.yigege.model.Coupon;
import top.yigege.service.ICouponService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 优惠券 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "优惠券(WEB)")
@RestController
@RequestMapping("/web/coupon")
@Validated
@Slf4j
public class WebCouponController {

    @Autowired
    ICouponService iCouponService;

    @ApiOperation("添加优惠券")
    @PostMapping("/addCoupon")
    public ResultBean addCoupon(@Valid AddCouponDTO addCouponDTO){
        Coupon coupon = new Coupon();
        BeanUtil.copyProperties(addCouponDTO, coupon);
        coupon.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        return ApiResultUtil.success(iCouponService.save(coupon));
    };

    @ApiOperation("修改优惠券")
    @PostMapping("/modifyCoupon")
    public ResultBean modifyCoupon(@Valid ModifyCouponDTO modifyCouponDTO) {
        Coupon Coupon = new Coupon();
        BeanUtil.copyProperties(modifyCouponDTO, Coupon);

        return ApiResultUtil.success(iCouponService.updateById(Coupon));
    }

    @ApiOperation("删除优惠券")
    @PostMapping("/deleteCouponByIds")
    public ResultBean deleteCouponByIds(@NotBlank(message = "优惠券id不能为空") String couponIds) {
        return ApiResultUtil.success(iCouponService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponIds))));
    }

    @ApiOperation("查询优惠券分页列表")
    @PostMapping("/queryCouponPageList")
    public LayuiTableResultBean queryCouponPageList(QueryCouponPageListDTO queryCouponPageListDTO) {
        queryCouponPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponService.queryCouponPageList(queryCouponPageListDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询优惠券详情")
    @PostMapping("/queryCouponDetail")
    public ResultBean queryCouponDetail(@NotNull(message = "优惠券id不能为空") Long couponId) {
        return ApiResultUtil.success(iCouponService.getById(couponId));
    }


    @ApiOperation("查询所有优惠券列表")
    @PostMapping("/queryAllCouponList")
    public ResultBean queryAllCouponList() {
        return ApiResultUtil.success(iCouponService.queryAllCouponList(Long.valueOf(SessionUtil.getUser().getUserId())));
    }

}
