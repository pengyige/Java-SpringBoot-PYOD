package top.yigege.controller.app;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.config.QrCodeConfig;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.ChargeOffReqDTO;
import top.yigege.dto.modules.coupon.QueryUserAvailableCouponPageListDTO;
import top.yigege.dto.modules.coupon.QueryUserCouponPageListDTO;
import top.yigege.dto.modules.couponDeduction.QueryChargeOffRecordPageListDTO;
import top.yigege.dto.modules.sysUser.MerchantUserLoginReqDTO;
import top.yigege.dto.modules.user.UserQrCodeDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.SysUser;
import top.yigege.model.User;
import top.yigege.service.ICouponDeductionService;
import top.yigege.service.ISysUserService;
import top.yigege.service.ITokenService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AppMerchantUserController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月31日 12:58
 */
@Api(tags = "APP-商家用户模块")
@RestController
@RequestMapping("/app/user")
@Validated
public class AppMerchantUserController {

    @Autowired
    QrCodeConfig qrCodeConfig;

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ITokenService iTokenService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Autowired
    ICouponDeductionService iCouponDeductionService;

    @ApiOperation("商家登入")
    @PostMapping("/login")
    public ResultBean login(@Valid MerchantUserLoginReqDTO merchantUserLoginReqDTO) {
        return ApiResultUtil.success(iSysUserService.login(merchantUserLoginReqDTO));
    }

    @ApiOperation("商家退出")
    @PostMapping("/logout")
    public ResultBean logout(@RequestAttribute Long userId) {
        return ApiResultUtil.success(iSysUserService.logout(userId));
    }

    @ApiOperation("扫描二维码内容")
    @PostMapping("/scanQrCode")
    public ResultBean scanQrCode(String content,@RequestAttribute Long userId) {
        String json = SecureUtil.aes(qrCodeConfig.getSecret().getBytes()).decryptStr(content);
        if (StringUtils.isBlank(json)) {
            throw new BusinessException(ResultCodeEnum.QRCODE_INVALID);
        }

        UserQrCodeDTO userQrCodeDTO = JSON.parseObject(json,UserQrCodeDTO.class);
        if (null == userQrCodeDTO) {
            throw new BusinessException(ResultCodeEnum.QRCODE_INVALID);
        }

        if (!userQrCodeDTO.getMerchantId().equals(userId)) {
            throw new BusinessException(ResultCodeEnum.QRCODE_INVALID);
        }
        //TODO 展示不处理二维码过期时间字段

        return ApiResultUtil.success(iTokenService.getUserId(userQrCodeDTO.getToken()));
    }

    @ApiOperation("查看用户可用的优惠券列表")
    @PostMapping("/queryUserAvailableCouponPageList")
    public ResultBean queryUserAvailableCouponPageList(@Valid QueryUserAvailableCouponPageListDTO queryUserAvailableCouponPageListDTO, @RequestAttribute Long userId) {
        User user = iUserService.getById(queryUserAvailableCouponPageListDTO.getUserId());
        if (!user.getMerchantId().equals(userId)) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_BUSINESS);
        }
        QueryUserCouponPageListDTO queryUserCouponPageListDTO = new QueryUserCouponPageListDTO();
        queryUserCouponPageListDTO.setUserId(user.getUserId());
        queryUserCouponPageListDTO.setVipCardId(user.getVipCardId());
        queryUserCouponPageListDTO.setPage(queryUserAvailableCouponPageListDTO.getPage());
        queryUserCouponPageListDTO.setPageSize(queryUserAvailableCouponPageListDTO.getPageSize());
        queryUserCouponPageListDTO.setCouponStatus(CouponStatusEnum.AVAILABLE.getCode());

        return ApiResultUtil.success(iUserCouponService.queryUserCouponPageList(queryUserCouponPageListDTO));
    }


    @ApiOperation("核销优惠券")
    @PostMapping("/chargeOff")
    public ResultBean chargeOff(@Valid ChargeOffReqDTO chargeOffReqDTO,@RequestAttribute Long userId){
        iSysUserService.chargeOff(chargeOffReqDTO,userId);
        return ApiResultUtil.success();
    }

    @ApiOperation("退回核销的优惠券")
    @PostMapping("/backChargeOff")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "couponDeductionId", value = "核销记录id", required = true, dataType = "Int"),
    })
    public ResultBean backChargeOff(@NotNull(message = "核销记录id不能为空") Long couponDeductionId){
        iSysUserService.backChargeOff(couponDeductionId);
        return ApiResultUtil.success();
    }


    @ApiOperation("查询核销记录")
    @PostMapping("/queryChargeOffRecordPageList")
    public ResultBean queryChargeOffRecordPageList(@Valid QueryChargeOffRecordPageListDTO queryChargeOffRecordPageListDTO
                                                   ) {
        return ApiResultUtil.success(iCouponDeductionService.queryChargeOffRecordPageList(queryChargeOffRecordPageListDTO));
    }
}
