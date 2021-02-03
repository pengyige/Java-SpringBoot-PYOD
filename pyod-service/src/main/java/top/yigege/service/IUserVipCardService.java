package top.yigege.service;

import top.yigege.dto.modules.userVipCard.BindUserVipCardDTO;
import top.yigege.dto.modules.userVipCard.ModiftVipCardBirthdayDTO;
import top.yigege.dto.modules.userVipCard.QueryUserVipCardInfoResDTO;
import top.yigege.model.UserVipCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IUserVipCardService extends IService<UserVipCard> {

    /**
     * 查询用户会员信息
     *
     * @param vipCardId
     * @return
     */
    UserVipCard queryUserVipCard(Long vipCardId);

    /**
     * 通过卡号查询会员信息
     * @param cardNo
     * @return
     */
    UserVipCard queryUserVipCardByCardNo(String cardNo);

    /**
     * 查询用户会员卡总数
     * @param userId
     * @return
     */
    int queryTotalUserVipCardCount(Long userId);

    /**
     * 添加新的vip卡
     * @param userId
     * @param cardCoverId
     * @param initBalance
     * @return
     */
    void addNewVipCard(Long merchantId,Long userId,Long cardCoverId,Double initBalance);


    /**
     * 绑定主卡
     * @param userId
     * @return
     */
    UserVipCard bindMainVipCard(Long merchantId,Long userId,Long cardCoverId,Double initBalance);

    /**
     * 充值
     * @param userId
     * @param vipCardId
     * @param price
     */
    void recharge(Long userId, Long vipCardId, Double price);

    /**
     * 查询用户所有vip卡片列表信息
     * @param userId
     * @return
     */
    List<QueryUserVipCardInfoResDTO> queryUserVipCardList(Long userId);

    /**
     * 绑定vip卡片
     * @param bindUserVipCardDTO
     */
    void bindVipCard(BindUserVipCardDTO bindUserVipCardDTO);

    /**
     * 修改会员卡生日
     * @param modiftVipCardBirthdayDTO
     */
    void modifyVipCardBirthday(ModiftVipCardBirthdayDTO modiftVipCardBirthdayDTO);

    /**
     * 查询用户会员卡详情
     * @param vipCardId
     * @return
     */
    QueryUserVipCardInfoResDTO queryUserVipCardDetail(Long vipCardId);

    /**
     * 解除绑定
     * @param vipCardId
     * @param userId
     */
    void unBindVipCard(Long vipCardId, Long userId);

    /**
     * 设置为主卡
     * @param vipCardId
     * @param userId
     */
    void setPrimaryCard(Long vipCardId, Long userId);
}
