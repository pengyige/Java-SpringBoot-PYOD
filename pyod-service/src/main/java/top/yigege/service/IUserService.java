package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.config.WxConfig;
import top.yigege.dto.modules.console.QueryHomeDataResDTO;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.ModifyUserInfoDTO;
import top.yigege.dto.modules.user.UpdateLocationDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserLoginResDTO;
import top.yigege.model.User;
import top.yigege.vo.ResultBean;

import java.util.List;

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
     * 查询该商家下所有用户
     * @param merchantId
     * @return
     */
    List<User> queryUserByMerchantId(Long merchantId);

    /**
     * 查询商家下所有用户数量
     * @param merchantId
     * @return
     */
    Long queryUserNumByMerchantId(Long merchantId);

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

    /**
     * 修改用户信息
     * @param modifyUserInfoDTO
     */
    void modifyUserInfo(ModifyUserInfoDTO modifyUserInfoDTO);

    /**
     * 通过手机号查询用户
     * @param merchantId
     * @param mobile
     * @return
     */
    User queryUserByMobile(Long merchantId, String mobile);

    /**
     * 查询主页数据
     * @param merchantId
     * @return
     */
    public QueryHomeDataResDTO queryHomeData(Integer merchantId) ;
}
