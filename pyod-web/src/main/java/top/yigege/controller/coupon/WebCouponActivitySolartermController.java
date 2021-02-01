package top.yigege.controller.coupon;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.AddCouponActivitySolartermDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivitySolartermDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivitySolartermPageListDTO;
import top.yigege.model.CouponActivitySolarterm;
import top.yigege.service.ICouponActivitySolartermService;
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
 * 系统任务 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "优惠券节气赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivitySolarterm")
@Validated
public class WebCouponActivitySolartermController {

    @Autowired
    ICouponActivitySolartermService iCouponActivitySolartermService;

    @ApiOperation("添加节气用户赠券")
    @PostMapping("/addCouponActivitySolarterm")
    public ResultBean addCouponActivitySolarterm(@Valid AddCouponActivitySolartermDTO addCouponActivitySolartermDTO){
        CouponActivitySolarterm couponActivityRegister = new CouponActivitySolarterm();
        BeanUtil.copyProperties(addCouponActivitySolartermDTO, couponActivityRegister);
        return ApiResultUtil.success(iCouponActivitySolartermService.save(couponActivityRegister));
    };

    @ApiOperation("修改节气赠券")
    @PostMapping("/modifyCouponActivitySolarterm")
    public ResultBean modifyCouponActivitySolarterm(@Valid ModifyCouponActivitySolartermDTO modifyCouponActivitySolartermDTO) {
        CouponActivitySolarterm CouponActivitySolarterm = new CouponActivitySolarterm();
        BeanUtil.copyProperties(modifyCouponActivitySolartermDTO, CouponActivitySolarterm);
        return ApiResultUtil.success(iCouponActivitySolartermService.updateById(CouponActivitySolarterm));
    }

    @ApiOperation("删除节气赠券")
    @PostMapping("/deleteCouponActivitySolartermByIds")
    public ResultBean deleteCouponActivitySolartermByIds(@NotBlank(message = "节气赠券id不能为空") String couponActivitySolartermIds) {
        return ApiResultUtil.success(iCouponActivitySolartermService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivitySolartermIds))));
    }

    @ApiOperation("查询节气赠券分页列表")
    @PostMapping("/queryCouponActivitySolartermPageList")
    public LayuiTableResultBean queryCouponActivitySolartermPageList(QueryCouponActivitySolartermPageListDTO queryCouponActivitySolartermPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivitySolartermPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivitySolartermService.queryCouponActivitySolartermPageList(queryCouponActivitySolartermPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询节气用户赠券详情")
    @PostMapping("/queryCouponActivitySolartermDetail")
    public ResultBean queryCouponActivitySolartermDetail(@NotNull(message = "节气赠券id不能为空") Long couponActivitySolartermId) {
        return ApiResultUtil.success(iCouponActivitySolartermService.getById(couponActivitySolartermId));
    }

}
