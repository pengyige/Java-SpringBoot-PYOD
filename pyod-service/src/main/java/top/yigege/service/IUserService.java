package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.config.WxConfig;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.UpdateLocationDTO;
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
    UserLoginResDTO loginByCode(WxConfig wxConfig,Long merchantId,String code);

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

    /**
     * 更新用户主卡
     * @param userId
     * @param primaryVipCardId
     */
    void updateUserPrimaryVipCard(Long userId, Long primaryVipCardId);

    /**
     * 增加豆豆
     * @param userId
     * @param peaNum
     */
    void addPea(Long userId,Double peaNum);

    /**
     * 退出登入
     * @param userId
     * @return
     */
    UserLoginResDTO logout(Long userId);

    /**
     * 更新用户定位
     * @param updateLocationDTO
     */
    void updateLocation(UpdateLocationDTO updateLocationDTO);
}
