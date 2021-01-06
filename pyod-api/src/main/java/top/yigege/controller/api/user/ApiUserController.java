package top.yigege.controller.api.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
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

}
