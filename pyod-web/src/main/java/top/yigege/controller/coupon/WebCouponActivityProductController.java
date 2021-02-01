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
import top.yigege.dto.modules.coupon.AddCouponActivityProductDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityProductDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityProductPageListDTO;
import top.yigege.model.CouponActivityProduct;
import top.yigege.service.ICouponActivityProductService;
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
@Api(tags = "优惠券购买商品赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityProduct")
@Validated
public class WebCouponActivityProductController {

    @Autowired
    ICouponActivityProductService iCouponActivityProductService;

    @ApiOperation("添加购买商品赠券")
    @PostMapping("/addCouponActivityProduct")
    public ResultBean addCouponActivityProduct(@Valid AddCouponActivityProductDTO addCouponActivityProductDTO){
        CouponActivityProduct couponActivityProduct = new CouponActivityProduct();
        BeanUtil.copyProperties(addCouponActivityProductDTO, couponActivityProduct);
        return ApiResultUtil.success(iCouponActivityProductService.save(couponActivityProduct));
    };

    @ApiOperation("修改购买商品赠券")
    @PostMapping("/modifyCouponActivityProduct")
    public ResultBean modifyCouponActivityProduct(@Valid ModifyCouponActivityProductDTO modifyCouponActivityProductDTO) {
        CouponActivityProduct CouponActivityProduct = new CouponActivityProduct();
        BeanUtil.copyProperties(modifyCouponActivityProductDTO, CouponActivityProduct);
        return ApiResultUtil.success(iCouponActivityProductService.updateById(CouponActivityProduct));
    }

    @ApiOperation("删除购买商品赠券")
    @PostMapping("/deleteCouponActivityProductByIds")
    public ResultBean deleteCouponActivityProductByIds(@NotBlank(message = "购买商品赠券id不能为空") String couponActivityProductIds) {
        return ApiResultUtil.success(iCouponActivityProductService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityProductIds))));
    }

    @ApiOperation("查询购买商品赠券分页列表")
    @PostMapping("/queryCouponActivityProductPageList")
    public LayuiTableResultBean queryCouponActivityProductPageList(QueryCouponActivityProductPageListDTO queryCouponActivityProductPageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityProductPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityProductService.queryCouponActivityProductPageList(queryCouponActivityProductPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询购买商品赠券详情")
    @PostMapping("/queryCouponActivityProductDetail")
    public ResultBean queryCouponActivityProductDetail(@NotNull(message = "购买商品赠券id不能为空") Long couponActivityProductId) {
        return ApiResultUtil.success(iCouponActivityProductService.getById(couponActivityProductId));
    }

}
