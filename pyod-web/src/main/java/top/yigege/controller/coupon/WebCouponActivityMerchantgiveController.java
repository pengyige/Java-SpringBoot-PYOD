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
import top.yigege.dto.modules.coupon.AddCouponActivityMerchantgiveDTO;

import top.yigege.dto.modules.coupon.QueryCouponActivityMerchantgivePageListDTO;
import top.yigege.model.CouponActivityMerchantgive;
import top.yigege.service.ICouponActivityMerchantgiveService;
import top.yigege.service.IUserService;
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
 * 商家赠券 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "商家赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityMerchantgive")
@Validated
public class WebCouponActivityMerchantgiveController {

    @Autowired
    ICouponActivityMerchantgiveService iCouponActivityMerchantgiveService;

    @Autowired
    IUserService iUserService;

    @ApiOperation("添加商家赠券")
    @PostMapping("/addCouponActivityMerchantgive")
    public ResultBean addCouponActivityMerchantgive(@Valid AddCouponActivityMerchantgiveDTO addCouponActivityMerchantgiveDTO){
        iCouponActivityMerchantgiveService.addCouponActivityMerchantgive(addCouponActivityMerchantgiveDTO);
        return ApiResultUtil.success();
    };

   /* @ApiOperation("修改商家赠券")
    @PostMapping("/modifyCouponActivityMerchantgive")
    public ResultBean modifyCouponActivityMerchantgive(@Valid ModifyCouponActivityMerchantgiveDTO modifyCouponActivityMerchantgiveDTO) {
        CouponActivityMerchantgive CouponActivityMerchantgive = new CouponActivityMerchantgive();
        BeanUtil.copyProperties(modifyCouponActivityMerchantgiveDTO, CouponActivityMerchantgive);
        return ApiResultUtil.success(iCouponActivityMerchantgiveService.updateById(CouponActivityMerchantgive));
    }*/

    @ApiOperation("删除商家赠券")
    @PostMapping("/deleteCouponActivityMerchantgiveByIds")
    public ResultBean deleteCouponActivityMerchantgiveByIds(@NotBlank(message = "商家赠券id不能为空") String couponActivityMerchantgiveIds) {
        return ApiResultUtil.success(iCouponActivityMerchantgiveService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityMerchantgiveIds))));
    }

    @ApiOperation("查询商家赠券分页列表")
    @PostMapping("/queryCouponActivityMerchantgivePageList")
    public LayuiTableResultBean queryCouponActivityMerchantgivePageList(QueryCouponActivityMerchantgivePageListDTO queryCouponActivityMerchantgivePageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityMerchantgivePageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityMerchantgiveService.queryCouponActivityMerchantgivePageList(queryCouponActivityMerchantgivePageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询商家赠券详情")
    @PostMapping("/queryCouponActivityMerchantgiveDetail")
    public ResultBean queryCouponActivityMerchantgiveDetail(@NotNull(message = "商家赠券id不能为空") Long couponActivityMerchantgiveId) {
        return ApiResultUtil.success(iCouponActivityMerchantgiveService.getById(couponActivityMerchantgiveId));
    }

    @ApiOperation("查询商家下所有用户总数")
    @PostMapping("/queryUserNum")
    public ResultBean queryUserNum(){
        return ApiResultUtil.success(iUserService.queryUserNumByMerchantId(Long.valueOf(SessionUtil.getUser().getUserId())));
    }
}
