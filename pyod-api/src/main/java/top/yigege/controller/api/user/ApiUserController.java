package top.yigege.controller.api.user;


import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.config.QrCodeConfig;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.ModifyUserInfoDTO;
import top.yigege.dto.modules.user.QueryOwnConsumptionRecordDTO;
import top.yigege.dto.modules.user.UpdateLocationDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserQrCodeDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.SysUser;
import top.yigege.model.User;
import top.yigege.model.UserVipCard;
import top.yigege.service.ISysUserService;
import top.yigege.service.IUserService;
import top.yigege.service.IUserVipCardService;
import top.yigege.service.impl.SysUserServiceImpl;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.QRCodeUtil;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@Validated
@Api(tags = "API-用户模块")
public class ApiUserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    QrCodeConfig qrCodeConfig;

    @Autowired
    IUserVipCardService iUserVipCardService;

    @Autowired
    ISysUserService sysUserService;

    /**
     * 通过code登入，若找到user，token有值;若无,token为""
     * @param code
     * @return
     */
    @ApiOperation(value = "通过code登入",notes = "若找到user，token有值;若无,token为空")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "微信登入code",dataType = "string",required = true,example = "0214vrll2eMwj64kxUml27HvrT24vrlU")})
    @PostMapping("/loginByCode")
    public ResultBean loginByCode(@NotNull(message = "商家ID不能为空") Long merchantId,@NotBlank(message = "登入code不能为空") String code) {
        return ApiResultUtil.success(iUserService.loginByCode(sysUserService.queryWxConfigByMerchantId(merchantId),merchantId,code));
    }

    @ApiOperation(value = "通过用户详细信息注册并登入")
    @PostMapping("/loginByUserDetail")
    public ResultBean loginByUserDetail(@Valid UserLoginDetailReqDTO userLoginDetailReqDTO) throws Exception {
        return ApiResultUtil.success(iUserService.loginByUserDetail(userLoginDetailReqDTO));
    }

    @ApiOperation(value = "绑定用户手机号")
    @PostMapping("/bindUserMobile")
    public ResultBean  bindUserMobile(@Valid BindWxUserMobileReqDTO bindWxUserMobileReqDTO) throws Exception {
        return  ApiResultUtil.success(iUserService.bindUserMobile(bindWxUserMobileReqDTO));
    }


    @ApiOperation(value = "获取个人二维码")
    @PostMapping("/getOwnVipCode")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token",dataType = "string",required = true)})

    public ResultBean getOwnVipCode(@RequestAttribute Long userId
            ,@NotBlank(message = "token不能为空") String token) throws Exception {
        User user = iUserService.getById(userId);
        String qrCode = "";
        if (null != user) {
            UserQrCodeDTO userQrCodeDTO = new UserQrCodeDTO();
            userQrCodeDTO.setToken(token);
            userQrCodeDTO.setMerchantId(user.getMerchantId());
            userQrCodeDTO.setExpireTime(System.currentTimeMillis()+qrCodeConfig.getDuration()*1000);
            String json = JSON.toJSONString(userQrCodeDTO);
            String encryptContent = SecureUtil.aes(qrCodeConfig.getSecret().getBytes()).encryptHex(json);
            log.info("encryptContent:{}",encryptContent);
            //String url = "https://admin.yigege.top/web/shop/toOrderPage?content="+encryptContent;
            qrCode = QRCodeUtil.encode(encryptContent);
        }else {
            throw new BusinessException(ResultCodeEnum.NO_USER);
        }

        return ApiResultUtil.success(qrCode);
    }


    @ApiOperation(value = "查询用户消费记录")
    @PostMapping("/queryOwnConsumptionRecord")
    public ResultBean queryOwnConsumptionRecord(@Valid QueryOwnConsumptionRecordDTO queryOwnConsumptionRecordDTO,
                                                Long userId) {
        queryOwnConsumptionRecordDTO.setUserId(userId);
        //TODO 待完善
        return ApiResultUtil.success();
    }

    @ApiOperation(value = "退出登入")
    @PostMapping("/logout")
    public ResultBean logout(@RequestAttribute Long userId) {
        return ApiResultUtil.success(iUserService.logout(userId));
    }

    @ApiOperation(value = "更新用户定位")
    @PostMapping("/updateLocation")
    public ResultBean updateLocation(@Valid UpdateLocationDTO updateLocationDTO,@RequestAttribute Long userId) {
        updateLocationDTO.setUserId(userId);
        iUserService.updateLocation(updateLocationDTO);
        return ApiResultUtil.success();
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/modifyUserInfo")
    public ResultBean modifyUserInfo(@Valid  ModifyUserInfoDTO modifyUserInfoDTO, @RequestAttribute Long userId) {
        modifyUserInfoDTO.setUserId(userId);
        iUserService.modifyUserInfo(modifyUserInfoDTO);
        return ApiResultUtil.success();
    }

    @ApiOperation(value = "查询用户信息")
    @PostMapping("/queryUserDetail")
    public ResultBean queryUserDetail( @RequestAttribute Long userId) {
        return ApiResultUtil.success(iUserService.queryUserDetail(userId));
    }


}
