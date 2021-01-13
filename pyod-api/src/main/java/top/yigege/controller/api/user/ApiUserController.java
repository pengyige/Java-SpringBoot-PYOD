package top.yigege.controller.api.user;


import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.config.QrCodeConfig;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserQrCodeDTO;
import top.yigege.model.User;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.QRCodeUtil;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
@Validated
public class ApiUserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    QrCodeConfig qrCodeConfig;

    /**
     * 通过code登入，若找到user，token有值;若无,token为""
     * @param code
     * @return
     */
    @PostMapping("/loginByCode")
    public ResultBean loginByCode(@NotBlank(message = "登入code不能为空") String code) {
        return ApiResultUtil.success(iUserService.loginByCode(code));
    }


    @PostMapping("/loginByUserDetail")
    public ResultBean loginByUserDetail(@Valid UserLoginDetailReqDTO userLoginDetailReqDTO) throws Exception {
        return ApiResultUtil.success(iUserService.loginByUserDetail(userLoginDetailReqDTO));
    }

    @PostMapping("/bindUserMobile")
    public ResultBean  bindUserMobile(@Valid BindWxUserMobileReqDTO bindWxUserMobileReqDTO) throws Exception {
        return  ApiResultUtil.success(iUserService.bindUserMobile(bindWxUserMobileReqDTO));
    }


    @PostMapping("/getOwnVipCode")
    public ResultBean getOwnVipCode(@RequestAttribute Long userId
            ,@NotBlank(message = "token不能为空") String token) throws Exception {
        User user = iUserService.getById(userId);
        String qrCode = "";
        if (null != user) {
            UserQrCodeDTO userQrCodeDTO = new UserQrCodeDTO();
            userQrCodeDTO.setToken(token);
            userQrCodeDTO.setExpireTime(System.currentTimeMillis()+qrCodeConfig.getDuration()*1000);
            String json = JSON.toJSONString(userQrCodeDTO);
            String encryptContent = SecureUtil.aes(qrCodeConfig.getSecret().getBytes()).encryptHex(json);
            qrCode = QRCodeUtil.encode(encryptContent);
        }
        return ApiResultUtil.success(qrCode);
    }



}