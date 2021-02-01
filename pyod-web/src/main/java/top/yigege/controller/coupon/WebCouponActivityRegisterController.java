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
import top.yigege.dto.modules.coupon.AddCouponActivityRegisterDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityRegisterDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityRegisterPageListDTO;
import top.yigege.model.CouponActivityRegister;
import top.yigege.service.ICouponActivityRegisterService;
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
@Api(tags = "优惠券新用户注册赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityRegister")
@Validated
public class WebCouponActivityRegisterController {

    @Autowired
    ICouponActivityRegisterService iCouponActivityRegisterService;

    @ApiOperation("添加新用户注册用户赠券")
    @PostMapping("/addCouponActivityRegister")
    public ResultBean addCouponActivityRegister(@Valid AddCouponActivityRegisterDTO addCouponActivityRegisterDTO){
        CouponActivityRegister couponActivityRegister = new CouponActivityRegister();
        BeanUtil.copyProperties(addCouponActivityRegisterDTO, couponActivityRegister);
        return ApiResultUtil.success(iCouponActivityRegisterService.save(couponActivityRegister));
    };

    @ApiOperation("修改新用户注册用户赠券")
    @PostMapping("/modifyCouponActivityRegister")
    public ResultBean modifyCouponActivityRegister(@Valid ModifyCouponActivityRegisterDTO modifyCouponActivityRegisterDTO) {
        CouponActivityRegister CouponActivityRegister = new CouponActivityRegister();
        BeanUtil.copyProperties(modifyCouponActivityRegisterDTO, CouponActivityRegister);
        return ApiResultUtil.success(iCouponActivityRegisterService.updateById(CouponActivityRegister));
    }

    @ApiOperation("删除新用户注册用户赠券")
    @PostMapping("/deleteCouponActivityRegisterByIds")
    public ResultBean deleteCouponActivityRegisterByIds(@NotBlank(message = "用户赠券id不能为空") String couponActivityRegisterIds) {
        return ApiResultUtil.success(iCouponActivityRegisterService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityRegisterIds))));
    }

    @ApiOperation("查询新用户注册用户赠券分页列表")
    @PostMapping("/queryCouponActivityRegisterPageList")
    public LayuiTableResultBean queryCouponActivityRegisterPageList(QueryCouponActivityRegisterPageListDTO queryCouponActivityRegisterPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityRegisterPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityRegisterService.queryCouponActivityRegisterPageList(queryCouponActivityRegisterPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询新用户注册用户赠券详情")
    @PostMapping("/queryCouponActivityRegisterDetail")
    public ResultBean queryCouponActivityRegisterDetail(@NotNull(message = "新用户注册用户赠券id不能为空") Long couponActivityRegisterId) {
        return ApiResultUtil.success(iCouponActivityRegisterService.queryCouponActivityRegisterWithCoupon(couponActivityRegisterId));
    }

}
