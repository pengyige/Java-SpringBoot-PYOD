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
import top.yigege.dto.modules.coupon.AddCouponActivityBirthdayDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityBirthdayDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityBirthdayPageListDTO;
import top.yigege.model.CouponActivityBirthday;
import top.yigege.service.ICouponActivityBirthdayService;
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
 * 生日赠券 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "生日赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityBirthday")
@Validated
public class WebCouponActivityBirthdayController {

    @Autowired
    ICouponActivityBirthdayService iCouponActivityBirthdayService;

    @ApiOperation("添加生日赠券")
    @PostMapping("/addCouponActivityBirthday")
    public ResultBean addCouponActivityBirthday(@Valid AddCouponActivityBirthdayDTO addCouponActivityBirthdayDTO){
        CouponActivityBirthday couponActivityBirthday = new CouponActivityBirthday();
        BeanUtil.copyProperties(addCouponActivityBirthdayDTO, couponActivityBirthday);
        return ApiResultUtil.success(iCouponActivityBirthdayService.save(couponActivityBirthday));
    };

    @ApiOperation("修改生日赠券")
    @PostMapping("/modifyCouponActivityBirthday")
    public ResultBean modifyCouponActivityBirthday(@Valid ModifyCouponActivityBirthdayDTO modifyCouponActivityBirthdayDTO) {
        CouponActivityBirthday CouponActivityBirthday = new CouponActivityBirthday();
        BeanUtil.copyProperties(modifyCouponActivityBirthdayDTO, CouponActivityBirthday);
        return ApiResultUtil.success(iCouponActivityBirthdayService.updateById(CouponActivityBirthday));
    }

    @ApiOperation("删除生日赠券")
    @PostMapping("/deleteCouponActivityBirthdayByIds")
    public ResultBean deleteCouponActivityBirthdayByIds(@NotBlank(message = "生日赠券id不能为空") String couponActivityBirthdayIds) {
        return ApiResultUtil.success(iCouponActivityBirthdayService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityBirthdayIds))));
    }

    @ApiOperation("查询生日赠券分页列表")
    @PostMapping("/queryCouponActivityBirthdayPageList")
    public LayuiTableResultBean queryCouponActivityBirthdayPageList(QueryCouponActivityBirthdayPageListDTO queryCouponActivityBirthdayPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityBirthdayPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityBirthdayService.queryCouponActivityBirthdayPageList(queryCouponActivityBirthdayPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询生日赠券详情")
    @PostMapping("/queryCouponActivityBirthdayDetail")
    public ResultBean queryCouponActivityBirthdayDetail(@NotNull(message = "生日赠券id不能为空") Long couponActivityBirthdayId) {
        return ApiResultUtil.success(iCouponActivityBirthdayService.getById(couponActivityBirthdayId));
    }

}
