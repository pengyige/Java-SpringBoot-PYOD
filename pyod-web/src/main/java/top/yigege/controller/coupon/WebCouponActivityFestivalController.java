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
import top.yigege.dto.modules.coupon.AddCouponActivityFestivalDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityFestivalDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityFestivalPageListDTO;
import top.yigege.model.CouponActivityFestival;
import top.yigege.service.ICouponActivityFestivalService;
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
 * 节日赠券 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "节日赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityFestival")
@Validated
public class WebCouponActivityFestivalController {

    @Autowired
    ICouponActivityFestivalService iCouponActivityFestivalService;

    @ApiOperation("添加节日赠券")
    @PostMapping("/addCouponActivityFestival")
    public ResultBean addCouponActivityFestival(@Valid AddCouponActivityFestivalDTO addCouponActivityFestivalDTO){
        CouponActivityFestival couponActivityFestival = new CouponActivityFestival();
        BeanUtil.copyProperties(addCouponActivityFestivalDTO, couponActivityFestival);
        return ApiResultUtil.success(iCouponActivityFestivalService.save(couponActivityFestival));
    };

    @ApiOperation("修改节日赠券")
    @PostMapping("/modifyCouponActivityFestival")
    public ResultBean modifyCouponActivityFestival(@Valid ModifyCouponActivityFestivalDTO modifyCouponActivityFestivalDTO) {
        CouponActivityFestival CouponActivityFestival = new CouponActivityFestival();
        BeanUtil.copyProperties(modifyCouponActivityFestivalDTO, CouponActivityFestival);
        return ApiResultUtil.success(iCouponActivityFestivalService.updateById(CouponActivityFestival));
    }

    @ApiOperation("删除节日赠券")
    @PostMapping("/deleteCouponActivityFestivalByIds")
    public ResultBean deleteCouponActivityFestivalByIds(@NotBlank(message = "节日赠券id不能为空") String couponActivityFestivalIds) {
        return ApiResultUtil.success(iCouponActivityFestivalService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityFestivalIds))));
    }

    @ApiOperation("查询节日赠券分页列表")
    @PostMapping("/queryCouponActivityFestivalPageList")
    public LayuiTableResultBean queryCouponActivityFestivalPageList(QueryCouponActivityFestivalPageListDTO queryCouponActivityFestivalPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityFestivalPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityFestivalService.queryCouponActivityFestivalPageList(queryCouponActivityFestivalPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询节日赠券详情")
    @PostMapping("/queryCouponActivityFestivalDetail")
    public ResultBean queryCouponActivityFestivalDetail(@NotNull(message = "节日赠券id不能为空") Long couponActivityFestivalId) {
        return ApiResultUtil.success(iCouponActivityFestivalService.getById(couponActivityFestivalId));
    }

}
