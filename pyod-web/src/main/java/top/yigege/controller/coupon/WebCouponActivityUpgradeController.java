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
import top.yigege.dto.modules.coupon.AddCouponActivityUpgradeDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityUpgradeDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityUpgradePageListDTO;
import top.yigege.model.CouponActivityUpgrade;
import top.yigege.service.ICouponActivityUpgradeService;
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
@Api(tags = "升级赠券(WEB)")
@RestController
@RequestMapping("/web/couponActivityUpgrade")
@Validated
public class WebCouponActivityUpgradeController {

    @Autowired
    ICouponActivityUpgradeService iCouponActivityUpgradeService;

    @ApiOperation("添加升级赠券")
    @PostMapping("/addCouponActivityUpgrade")
    public ResultBean addCouponActivityUpgrade(@Valid AddCouponActivityUpgradeDTO addCouponActivityUpgradeDTO){
        CouponActivityUpgrade couponActivityRegister = new CouponActivityUpgrade();
        BeanUtil.copyProperties(addCouponActivityUpgradeDTO, couponActivityRegister);
        return ApiResultUtil.success(iCouponActivityUpgradeService.save(couponActivityRegister));
    };

    @ApiOperation("修改升级赠券")
    @PostMapping("/modifyCouponActivityUpgrade")
    public ResultBean modifyCouponActivityUpgrade(@Valid ModifyCouponActivityUpgradeDTO modifyCouponActivityUpgradeDTO) {
        CouponActivityUpgrade CouponActivityUpgrade = new CouponActivityUpgrade();
        BeanUtil.copyProperties(modifyCouponActivityUpgradeDTO, CouponActivityUpgrade);
        return ApiResultUtil.success(iCouponActivityUpgradeService.updateById(CouponActivityUpgrade));
    }

    @ApiOperation("删除升级赠券")
    @PostMapping("/deleteCouponActivityUpgradeByIds")
    public ResultBean deleteCouponActivityUpgradeByIds(@NotBlank(message = "用户赠券id不能为空") String couponActivityUpgradeIds) {
        return ApiResultUtil.success(iCouponActivityUpgradeService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityUpgradeIds))));
    }

    @ApiOperation("查询升级赠券分页列表")
    @PostMapping("/queryCouponActivityUpgradePageList")
    public LayuiTableResultBean queryCouponActivityUpgradePageList(QueryCouponActivityUpgradePageListDTO queryCouponActivityUpgradePageListDTO) {
        PageBean pageBean = new PageBean();
        queryCouponActivityUpgradePageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityUpgradeService.queryCouponActivityUpgradePageList(queryCouponActivityUpgradePageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询升级赠券详情")
    @PostMapping("/queryCouponActivityUpgradeDetail")
    public ResultBean queryCouponActivityUpgradeDetail(@NotNull(message = "升级赠券id不能为空") Long couponActivityUpgradeId) {
        return ApiResultUtil.success(iCouponActivityUpgradeService.getById(couponActivityUpgradeId));
    }

}
