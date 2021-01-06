package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserLoginResDTO;
import top.yigege.model.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-05
 */
public interface IUserService extends IService<User> {

    /**
     * 通过code登入
     * @param code
     * @return
     */
    UserLoginResDTO loginByCode(String code);

    /**
     * 通过用户基础信息注册并登入
     * @param userLoginDetailReqDTO
     * @return
     */
    UserLoginResDTO loginByUserDetail(UserLoginDetailReqDTO userLoginDetailReqDTO) throws Exception;


    /**
     * 绑定用户手机号
     * @param bindWxUserMobileReqDTO
     * @return
     */
    UserLoginResDTO bindUserMobile(BindWxUserMobileReqDTO bindWxUserMobileReqDTO) throws Exception;
}
