package top.yigege.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.constant.YesOrNo;
import top.yigege.dto.modules.userVipCard.BindUserVipCardDTO;
import top.yigege.dto.modules.userVipCard.ModiftVipCardBirthdayDTO;
import top.yigege.dto.modules.userVipCard.QueryUserVipCardInfoResDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.model.UserVipCard;
import top.yigege.dao.UserVipCardMapper;
import top.yigege.model.VipCard;
import top.yigege.service.IRedisService;
import top.yigege.service.IUserService;
import top.yigege.service.IUserVipCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.service.IVipCardService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class UserVipCardServiceImpl extends ServiceImpl<UserVipCardMapper, UserVipCard> implements IUserVipCardService {

    @Resource
    UserVipCardMapper userVipCardMapper;

    @Autowired
    IVipCardService iVipCardService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IRedisService iRedisService;

    @Override
    public UserVipCard queryUserVipCard(Long vipCardId) {
        LambdaQueryWrapper<UserVipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserVipCard::getVipCardId,vipCardId);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public UserVipCard queryUserVipCardByCardNo(String cardNo) {
        LambdaQueryWrapper<UserVipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserVipCard::getCardNo,cardNo);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public int queryTotalUserVipCardCount(Long userId) {
        LambdaQueryWrapper<UserVipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserVipCard::getUserId,userId);
        return count(lambdaQueryWrapper);
    }


    @Override
    public void addNewVipCard(Long merchantId,Long userId,Long cardCoverId,Double initBalance){
        VipCard vipCard = iVipCardService.generatorVipCard(merchantId,cardCoverId);
        UserVipCard userVipCard = new UserVipCard();
        userVipCard.setUserId(userId);
        userVipCard.setVipCardId(vipCard.getVipCardId());
        userVipCard.setCardNo(vipCard.getCardNo());
        userVipCard.setCardPsw(DigestUtil.md5Hex(PyodConstant.Default.VIP_PASS_DEFAULT));
        userVipCard.setPrimaryFlag(YesOrNo.NO.getCode());
        userVipCard.setBalance(initBalance);
        save(userVipCard);
    }

    @Transactional
    @Override
    public UserVipCard bindMainVipCard(Long merchantId,Long userId,Long cardCoverId,Double initBalance) {
        VipCard vipCard = iVipCardService.generatorVipCard(merchantId,cardCoverId);
        UserVipCard userVipCard = new UserVipCard();
        userVipCard.setUserId(userId);
        userVipCard.setVipCardId(vipCard.getVipCardId());
        userVipCard.setCardNo(vipCard.getCardNo());
        userVipCard.setCardPsw(DigestUtil.md5Hex(PyodConstant.Default.VIP_PASS_DEFAULT));
        userVipCard.setPrimaryFlag(YesOrNo.YES.getCode());
        userVipCard.setBalance(initBalance);
        save(userVipCard);
        //更新用户主卡id
        iUserService.updateUserPrimaryVipCard(userId,userVipCard.getVipCardId());

        return userVipCard;
    }

    @Override
    public void recharge(Long userId, Long vipCardId, Double price) {
        LambdaQueryWrapper<UserVipCard> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(UserVipCard::getUserId,userId);
        lambdaQueryWrapper.eq(UserVipCard::getVipCardId,vipCardId);
        UserVipCard userVipCard = getOne(lambdaQueryWrapper);
        if (null != userVipCard) {
            userVipCard.setBalance(NumberUtil.add(userVipCard.getBalance(),price));
            updateById(userVipCard);
        }
    }

    @Override
    public List<QueryUserVipCardInfoResDTO> queryUserVipCardList(Long userId) {
        return userVipCardMapper.queryUserVipCardList(userId);
    }

    @Override
    public void bindVipCard(BindUserVipCardDTO bindUserVipCardDTO) {
        VipCard vipCard = iVipCardService.queryVipCardByCardNo(bindUserVipCardDTO.getCardNo());
        if (null == vipCard) {
            throw new BusinessException(ResultCodeEnum.NO_CARD);
        }

        UserVipCard userVipCard = queryUserVipCardByCardNo(bindUserVipCardDTO.getCardNo());
        if (null != userVipCard) {
            throw new BusinessException(ResultCodeEnum.CARD_UESD);
        }

        userVipCard = new UserVipCard();
        userVipCard.setUserId(bindUserVipCardDTO.getUserId());
        userVipCard.setVipCardId(vipCard.getVipCardId());
        userVipCard.setCardNo(vipCard.getCardNo());
        userVipCard.setCardPsw(DigestUtil.md5Hex(bindUserVipCardDTO.getCardPsw()));
        userVipCard.setPrimaryFlag(bindUserVipCardDTO.getPrimaryFlag());
        userVipCard.setBalance(0.0d);
        save(userVipCard);
    }

    @Override
    public void modifyVipCardBirthday(ModiftVipCardBirthdayDTO modiftVipCardBirthdayDTO) {
        UserVipCard userVipCard = queryUserVipCard(modiftVipCardBirthdayDTO.getVipCardId());
        if (null != userVipCard.getBirthday()) {
            throw new BusinessException(ResultCodeEnum.VIP_CARD_BIRTHDAY_MODIFY_LIMIT);
        }

        userVipCard.setBirthday(modiftVipCardBirthdayDTO.getBirthday());
        updateById(userVipCard);

        //设置生日到期事件,到期后给该卡赠送优惠券
      /*  String birthdayKey = RedisKeyEnum.BIRTHDAY_EVENT.getKey() + modiftVipCardBirthdayDTO.getUserId() + ":" + userVipCard.getVipCardId();
        Date registerDate = userVipCard.getCreateTime();
        Date birthdayDate = DateUtil.offsetMonth(registerDate, 12);
        iRedisService.setObj(birthdayKey, modiftVipCardBirthdayDTO.getUserId(), birthdayDate.getTime() - registerDate.getTime());
*/
    }

    @Override
    public QueryUserVipCardInfoResDTO queryUserVipCardDetail(Long vipCardId) {
        return userVipCardMapper.queryUserVipCardDetail(vipCardId);
    }

    @Override
    public void unBindVipCard(Long vipCardId, Long userId) {
        LambdaQueryWrapper<UserVipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserVipCard::getVipCardId,vipCardId);
        lambdaQueryWrapper.eq(UserVipCard::getUserId,userId);
        UserVipCard userVipCard = getOne(lambdaQueryWrapper);
        if (null == userVipCard) {
            throw new BusinessException(ResultCodeEnum.NO_CARD);
        }
        if (YesOrNo.YES.getCode().equals(userVipCard.getPrimaryFlag())) {
            //如果是主卡更新用户
            iUserService.updateUserPrimaryVipCard(userId,0L);
        }

        removeById(userVipCard);
    }

    @Transactional
    @Override
    public void setPrimaryCard(Long vipCardId, Long userId) {
        LambdaQueryWrapper<UserVipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //lambdaQueryWrapper.eq(UserVipCard::getVipCardId,vipCardId);
        lambdaQueryWrapper.eq(UserVipCard::getUserId,userId);
        List<UserVipCard> userVipCardList = list(lambdaQueryWrapper);
        userVipCardList.forEach(item -> {
            if (!item.getVipCardId().equals(vipCardId)) {
               item.setPrimaryFlag(YesOrNo.NO.getCode());
            }else {
                item.setPrimaryFlag(YesOrNo.YES.getCode());
            }
        });

        updateBatchById(userVipCardList);

        iUserService.updateUserPrimaryVipCard(userId,vipCardId);


    }
}
