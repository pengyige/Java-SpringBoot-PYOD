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
import top.yigege.dto.modules.coupon.AddCouponActivityCdkeyDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityCdkeyDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityCdkeyPageListDTO;
import top.yigege.model.CouponActivityCdkey;
import top.yigege.service.ICouponActivityCdkeyService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统任务 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "CDKEY(WEB)")
@RestController
@RequestMapping("/web/couponActivityCdkey")
@Validated
public class WebCouponActivityCdkeyController {

    @Autowired
    ICouponActivityCdkeyService iCouponActivityCdkeyService;

    @ApiOperation("添加CDKEY")
    @PostMapping("/addCouponActivityCdkey")
    public ResultBean addCouponActivityCdkey(@Valid AddCouponActivityCdkeyDTO addCouponActivityCdkeyDTO){

        iCouponActivityCdkeyService.batchAddCdkey(addCouponActivityCdkeyDTO);
        return ApiResultUtil.success();
    };

    @ApiOperation("修改CDKEY")
    @PostMapping("/modifyCouponActivityCdkey")
    public ResultBean modifyCouponActivityCdkey(@Valid ModifyCouponActivityCdkeyDTO modifyCouponActivityCdkeyDTO) {
        CouponActivityCdkey CouponActivityCdkey = new CouponActivityCdkey();
        BeanUtil.copyProperties(modifyCouponActivityCdkeyDTO, CouponActivityCdkey);
        return ApiResultUtil.success(iCouponActivityCdkeyService.updateById(CouponActivityCdkey));
    }

    @ApiOperation("删除CDKEY")
    @PostMapping("/deleteCouponActivityCdkeyByIds")
    public ResultBean deleteCouponActivityCdkeyByIds(@NotBlank(message = "CDKEYid不能为空") String couponActivityCdkeyIds) {
        return ApiResultUtil.success(iCouponActivityCdkeyService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityCdkeyIds))));
    }

    @ApiOperation("查询CDKEY分页列表")
    @PostMapping("/queryCouponActivityCdkeyPageList")
    public LayuiTableResultBean queryCouponActivityCdkeyPageList(QueryCouponActivityCdkeyPageListDTO queryCouponActivityCdkeyPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityCdkeyPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityCdkeyService.queryCouponActivityCdkeyPageList(queryCouponActivityCdkeyPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询CDKEY详情")
    @PostMapping("/queryCouponActivityCdkeyDetail")
    public ResultBean queryCouponActivityCdkeyDetail(@NotNull(message = "CDKEYid不能为空") Long couponActivityCdkeyId) {
        return ApiResultUtil.success(iCouponActivityCdkeyService.getById(couponActivityCdkeyId));
    }

}
