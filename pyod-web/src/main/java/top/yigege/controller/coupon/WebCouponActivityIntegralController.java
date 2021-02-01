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
import top.yigege.dto.modules.coupon.AddCouponActivityIntegralDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityIntegralDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityIntegralPageListDTO;
import top.yigege.model.CouponActivityIntegral;
import top.yigege.service.ICouponActivityIntegralService;
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
 * 积分 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "优惠券积分赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityIntegral")
@Validated
public class WebCouponActivityIntegralController {

    @Autowired
    ICouponActivityIntegralService iCouponActivityIntegralService;

    @ApiOperation("添加积分赠券")
    @PostMapping("/addCouponActivityIntegral")
    public ResultBean addCouponActivityIntegral(@Valid AddCouponActivityIntegralDTO addCouponActivityIntegralDTO){
        CouponActivityIntegral couponActivityIntegral = new CouponActivityIntegral();
        BeanUtil.copyProperties(addCouponActivityIntegralDTO, couponActivityIntegral);
        return ApiResultUtil.success(iCouponActivityIntegralService.save(couponActivityIntegral));
    };

    @ApiOperation("修改积分赠券")
    @PostMapping("/modifyCouponActivityIntegral")
    public ResultBean modifyCouponActivityIntegral(@Valid ModifyCouponActivityIntegralDTO modifyCouponActivityIntegralDTO) {
        CouponActivityIntegral CouponActivityIntegral = new CouponActivityIntegral();
        BeanUtil.copyProperties(modifyCouponActivityIntegralDTO, CouponActivityIntegral);
        return ApiResultUtil.success(iCouponActivityIntegralService.updateById(CouponActivityIntegral));
    }

    @ApiOperation("删除积分赠券")
    @PostMapping("/deleteCouponActivityIntegralByIds")
    public ResultBean deleteCouponActivityIntegralByIds(@NotBlank(message = "积分赠券id不能为空") String couponActivityIntegralIds) {
        return ApiResultUtil.success(iCouponActivityIntegralService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityIntegralIds))));
    }

    @ApiOperation("查询积分赠券分页列表")
    @PostMapping("/queryCouponActivityIntegralPageList")
    public LayuiTableResultBean queryCouponActivityIntegralPageList(QueryCouponActivityIntegralPageListDTO queryCouponActivityIntegralPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityIntegralPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityIntegralService.queryCouponActivityIntegralPageList(queryCouponActivityIntegralPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询积分赠券详情")
    @PostMapping("/queryCouponActivityIntegralDetail")
    public ResultBean queryCouponActivityIntegralDetail(@NotNull(message = "积分赠券id不能为空") Long couponActivityIntegralId) {
        return ApiResultUtil.success(iCouponActivityIntegralService.getById(couponActivityIntegralId));
    }

}
