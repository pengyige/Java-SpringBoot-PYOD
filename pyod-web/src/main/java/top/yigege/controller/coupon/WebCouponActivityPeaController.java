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
import top.yigege.dto.modules.coupon.AddCouponActivityPeaDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityPeaDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityPeaPageListDTO;
import top.yigege.model.CouponActivityPea;
import top.yigege.service.ICouponActivityPeaService;
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
 * 积豆 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "优惠券积豆赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityPea")
@Validated
public class WebCouponActivityPeaController {

    @Autowired
    ICouponActivityPeaService iCouponActivityPeaService;

    @ApiOperation("添加积豆赠券")
    @PostMapping("/addCouponActivityPea")
    public ResultBean addCouponActivityPea(@Valid AddCouponActivityPeaDTO addCouponActivityPeaDTO){
        CouponActivityPea couponActivityIntegral = new CouponActivityPea();
        BeanUtil.copyProperties(addCouponActivityPeaDTO, couponActivityIntegral);
        return ApiResultUtil.success(iCouponActivityPeaService.save(couponActivityIntegral));
    };

    @ApiOperation("修改积豆赠券")
    @PostMapping("/modifyCouponActivityPea")
    public ResultBean modifyCouponActivityPea(@Valid ModifyCouponActivityPeaDTO modifyCouponActivityPeaDTO) {
        CouponActivityPea CouponActivityPea = new CouponActivityPea();
        BeanUtil.copyProperties(modifyCouponActivityPeaDTO, CouponActivityPea);
        return ApiResultUtil.success(iCouponActivityPeaService.updateById(CouponActivityPea));
    }

    @ApiOperation("删除积豆赠券")
    @PostMapping("/deleteCouponActivityPeaByIds")
    public ResultBean deleteCouponActivityPeaByIds(@NotBlank(message = "积豆赠券id不能为空") String couponActivityPeaIds) {
        return ApiResultUtil.success(iCouponActivityPeaService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityPeaIds))));
    }

    @ApiOperation("查询积豆赠券分页列表")
    @PostMapping("/queryCouponActivityPeaPageList")
    public LayuiTableResultBean queryCouponActivityPeaPageList(QueryCouponActivityPeaPageListDTO queryCouponActivityPeaPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityPeaPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityPeaService.queryCouponActivityPeaPageList(queryCouponActivityPeaPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询积豆赠券详情")
    @PostMapping("/queryCouponActivityPeaDetail")
    public ResultBean queryCouponActivityPeaDetail(@NotNull(message = "积豆赠券id不能为空") Long couponActivityPeaId) {
        return ApiResultUtil.success(iCouponActivityPeaService.getById(couponActivityPeaId));
    }

}
