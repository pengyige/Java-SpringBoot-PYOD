package top.yigege.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.yigege.config.WxConfig;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.DictCodeEnum;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dao.UserMapper;
import top.yigege.dto.modules.console.QueryHomeDataResDTO;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.ModifyUserInfoDTO;
import top.yigege.dto.modules.user.UpdateLocationDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserLoginResDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.Coupon;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityPea;
import top.yigege.model.CouponActivityRegister;
import top.yigege.model.CouponActivityUpgrade;
import top.yigege.model.Label;
import top.yigege.model.Level;
import top.yigege.model.SysDict;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.model.UserVipCard;
import top.yigege.model.VipCard;
import top.yigege.service.ICardCoverService;
import top.yigege.service.ICouponActivityPeaService;
import top.yigege.service.ICouponActivityRegisterService;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponActivityUpgradeService;
import top.yigege.service.ICouponService;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.ILevelService;
import top.yigege.service.IRedisService;
import top.yigege.service.ISysDictService;
import top.yigege.service.ITokenService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;
import top.yigege.service.IUserVipCardService;
import top.yigege.service.IVipCardService;
import top.yigege.util.AEScbcUtil;
import top.yigege.util.WeixinUtil;
import top.yigege.vo.wx.Code2SessionResultBean;
import top.yigege.vo.wx.WxMobileDataBean;
import top.yigege.vo.wx.WxUserInfoDataBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static top.yigege.constant.ResultCodeEnum.NO_USER;
import static top.yigege.constant.ResultCodeEnum.REDIS_SESSION_KEY_EXPIRE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Autowired
    WxConfig wxConfig;

    @Autowired
    WeixinUtil weixinUtil;

    @Autowired
    IGenerateIDService iGenerateIDService;

    @Autowired
    ITokenService iTokenService;

    @Autowired
    IRedisService iRedisService;

    @Autowired
    ISysDictService iSysDictService;

    @Autowired
    ILevelService iLevelService;

    @Autowired
    IUserVipCardService iUserVipCardService;

    @Autowired
    ICouponService iCouponService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    ICouponActivityRegisterService iCouponActivityRegisterService;

    @Autowired
    ICouponActivityUpgradeService iCouponActivityUpgradeService;

    @Autowired
    ICouponActivityPeaService iCouponActivityPeaService;

    @Autowired
    ICardCoverService iCardCoverService;


    public User queryUniqueUser(String openId,Long merchantId) {
        //根据openId判断用户是否已存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getOpenid, openId);
        userLambdaQueryWrapper.eq(User::getMerchantId, merchantId);
        User user = getOne(userLambdaQueryWrapper);
        return user;
    }

    @Override
    public List<User> queryUserByMerchantId(Long merchantId) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getMerchantId, merchantId);
        return list(userLambdaQueryWrapper);
    }

    @Override
    public Long queryUserNumByMerchantId(Long merchantId) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getMerchantId, merchantId);
        return Long.valueOf(count(userLambdaQueryWrapper));
    }

    @Override
    public UserLoginResDTO loginByCode(WxConfig wxConfig,Long merchantId,String code) {
        Code2SessionResultBean code2SessionResultBean = weixinUtil.getCode2Session(wxConfig, code);
        if (0 != code2SessionResultBean.getErrcode()) {
            throw new BusinessException(ResultCodeEnum.CALL_WEIXIN_API_ERROR.getCode(),
                    code2SessionResultBean.getErrcode() + "-" + code2SessionResultBean.getErrmsg());
        }

        User user = queryUniqueUser(code2SessionResultBean.getOpenid(),merchantId);
        UserLoginResDTO userLoginResDTO = new UserLoginResDTO();
        if (null != user) {
            fillData(user, userLoginResDTO);
        } else {
            userLoginResDTO.setOpenid(code2SessionResultBean.getOpenid());
            userLoginResDTO.setToken("");
            userLoginResDTO.setMobile("");
            userLoginResDTO.setMerchantId(merchantId);
        }
        //将openid对应的session_key缓存到redis
        iRedisService.setObj(code2SessionResultBean.getOpenid(), code2SessionResultBean.getSession_key(), 300);

        return userLoginResDTO;
    }


    @Transactional
    @Override
    public UserLoginResDTO loginByUserDetail(UserLoginDetailReqDTO userLoginDetailReqDTO) throws Exception {
        Object sessionKey = iRedisService.getObj(userLoginDetailReqDTO.getOpenid());
        if (null == sessionKey) {
            throw new BusinessException(REDIS_SESSION_KEY_EXPIRE);
        }
        String resultByte = AEScbcUtil.decrypt(userLoginDetailReqDTO.getEncryptedData()
                , String.valueOf(sessionKey)
                , userLoginDetailReqDTO.getIv()
                , "utf-8");

        WxUserInfoDataBean wxUserInfoDataBean = JSONUtil.toBean(resultByte, WxUserInfoDataBean.class);


        User user = queryUniqueUser(userLoginDetailReqDTO.getOpenid(), userLoginDetailReqDTO.getMerchantId());
        UserLoginResDTO userLoginResDTO = new UserLoginResDTO();
        if (null != user) {
            fillData(user, userLoginResDTO);
        } else {
            User newUser = new User();
            newUser.setMerchantId(userLoginDetailReqDTO.getMerchantId());
            newUser.setQrCodeNo(iGenerateIDService.getNo(""));

            newUser.setNickname(wxUserInfoDataBean.getNickName());
            newUser.setAvatar(wxUserInfoDataBean.getAvatarUrl());
            newUser.setSex(wxUserInfoDataBean.getGender());
            newUser.setOpenid(userLoginDetailReqDTO.getOpenid());


            //初始等级
            Level defaultLevel = iLevelService.queryDefaultLevel(userLoginDetailReqDTO.getMerchantId());
            newUser.setLevelId(defaultLevel.getLevelId());

            newUser.setTotalPeaNum(0d);
            newUser.setAvaliablePeaNum(0d);
            newUser.setAvaliableIntegrate(0);
            newUser.setTotalIntegrate(0);

            //设置豆豆过期时间
            String key = "";
            SysDict sysDict = iSysDictService.queryDictByCode(DictCodeEnum.COIN_CLEAR_DURATION_TIME.getCode());
            if (null != sysDict && StringUtils.isNotBlank(sysDict.getCode())) {
                long expireTime = System.currentTimeMillis() + Long.parseLong(sysDict.getValue()) * 1000;
                newUser.setExpireTime(new Date(expireTime));
            }

            save(newUser);
            if (null != newUser.getExpireTime()) {
                //保存到redis,通过失效key事件处理
                key = RedisKeyEnum.PEA_EXPIRE_EVENT.getKey() + newUser.getUserId();
                iRedisService.setObj(key, newUser.getUserId(), Long.parseLong(sysDict.getValue()));
            }

            //免费生成主卡
            UserVipCard userVipCard = iUserVipCardService.bindMainVipCard(userLoginDetailReqDTO.getMerchantId()
                    , newUser.getUserId()
                    , iCardCoverService.queryDefaultCardCover(userLoginDetailReqDTO.getMerchantId()).getCardCoverId()
                    , 0d);
            newUser.setVipCardId(userVipCard.getVipCardId());

            //新用户注册赠券活动是否开启
            CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(userLoginDetailReqDTO.getMerchantId(),ActivityTypeEnum.NEW_USER_REGISTER);
            if (null != couponActivity) {
                //查询对应券，赠送给用户
                LambdaQueryWrapper<CouponActivityRegister> couponActivityRegisterLambdaQueryWrapper = new LambdaQueryWrapper<>();
                couponActivityRegisterLambdaQueryWrapper.eq(CouponActivityRegister::getCouponActivityId, couponActivity.getCouponActivityId());
                List<CouponActivityRegister> couponActivityRegisters = iCouponActivityRegisterService.list(couponActivityRegisterLambdaQueryWrapper);
                if (!couponActivityRegisters.isEmpty()) {
                    List<UserCoupon> userCouponList = new ArrayList<>();
                    for (CouponActivityRegister couponActivityRegister: couponActivityRegisters) {
                        for (int i = 0 ; i < couponActivityRegister.getNum(); i++) {
                            UserCoupon userCoupon = new UserCoupon();
                            userCoupon.setUserId(newUser.getUserId());
                            userCoupon.setVipCardId(newUser.getVipCardId());
                            userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                            userCoupon.setCouponId(couponActivityRegister.getCouponId());
                            userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivityRegister.getCouponId(), newUser.getCreateTime()));
                            userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                            userCouponList.add(userCoupon);
                        }
                    }
                    //保存
                    iUserCouponService.batchAddUserCoupon(userCouponList);
                }
            }

            fillData(newUser, userLoginResDTO);
        }

        return userLoginResDTO;
    }

    @Override
    public UserLoginResDTO bindUserMobile(BindWxUserMobileReqDTO bindWxUserMobileReqDTO) throws Exception {
        Object sessionKey = iRedisService.getObj(bindWxUserMobileReqDTO.getOpenid());
        if (null == sessionKey) {
            throw new BusinessException(REDIS_SESSION_KEY_EXPIRE);
        }
        String resultByte = AEScbcUtil.decrypt(bindWxUserMobileReqDTO.getEncryptedData()
                , String.valueOf(sessionKey)
                , bindWxUserMobileReqDTO.getIv()
                , "utf-8");

        WxMobileDataBean wxMobileDataBean = JSONUtil.toBean(resultByte, WxMobileDataBean.class);

        User user = queryUniqueUser(bindWxUserMobileReqDTO.getOpenid(),bindWxUserMobileReqDTO.getMerchantId());
        UserLoginResDTO userLoginResDTO = new UserLoginResDTO();

        if (null == user) {
            throw new BusinessException(NO_USER);
        } else {
            user.setMobile(wxMobileDataBean.getPhoneNumber());
            updateById(user);
            fillData(user, userLoginResDTO);
        }

        return userLoginResDTO;
    }

    @Override
    public void updateUserPrimaryVipCard(Long userId, Long primaryVipCardId) {
        //更新用户主卡信息
        User user = getById(userId);
        user.setVipCardId(primaryVipCardId);
        updateById(user);
    }

    @Transactional
    @Override
    public void addPea(Long userId, Double peaNum) {
        User user = getById(userId);

        Level currentLevel = iLevelService.getById(user.getLevelId());
        //TODO 1.增加累计豆豆，根据豆豆数据升级，升级时判断升级活动是否开启并赠券
        user.setTotalPeaNum(NumberUtil.add(user.getTotalPeaNum(),peaNum));
        user.setAvaliablePeaNum(NumberUtil.add(user.getAvaliablePeaNum(),peaNum));

        if (user.getTotalPeaNum() >= currentLevel.getMaxValue()) {
            //等级信息
            LambdaQueryWrapper<Level> levelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            levelLambdaQueryWrapper.eq(Level::getMerchantId,user.getMerchantId());
            List<Level> levelList = iLevelService.list(levelLambdaQueryWrapper);
            levelList.sort((level1, level2) -> {
                return level1.getMinValue() - level2.getMinValue();
            });
            Level nextLevel = null;
            for (int i = 0; i < levelList.size(); i++) {
                if (user.getLevelId().equals(levelList.get(i).getLevelId())) {
                    currentLevel = levelList.get(i);
                    if (i != levelList.size() - 1) {
                        nextLevel = levelList.get(i + 1);
                    }
                    break;
                }
            }

            if (null != nextLevel) {
                //未满级，升级
                user.setLevelId(nextLevel.getLevelId());
                updateById(user);

                //升级赠券活动是否开启
                CouponActivity upgradeCouponActivity = iCouponActivityService.queryUnderwayActivity(user.getMerchantId(),ActivityTypeEnum.UPGRADE);
                Date getDate = new Date();
                if (null != upgradeCouponActivity) {
                    //查询对应券，赠送给用户
                    LambdaQueryWrapper<CouponActivityUpgrade> couponActivityUpgradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    couponActivityUpgradeLambdaQueryWrapper.eq(CouponActivityUpgrade::getCouponActivityId, upgradeCouponActivity.getCouponActivityId());
                    couponActivityUpgradeLambdaQueryWrapper.eq(CouponActivityUpgrade::getLevelId, user.getLevelId());
                    List<CouponActivityUpgrade> couponActivityUpgradeList = iCouponActivityUpgradeService.list(couponActivityUpgradeLambdaQueryWrapper);
                    if (!couponActivityUpgradeList.isEmpty()) {
                        List<UserCoupon> userCouponList = new ArrayList<>();
                        for (CouponActivityUpgrade couponActivityUpgrade : couponActivityUpgradeList) {
                            for (int i = 0; i < couponActivityUpgrade.getNum(); i++) {
                                UserCoupon userCoupon = new UserCoupon();
                                userCoupon.setUserId(user.getUserId());
                                userCoupon.setVipCardId(user.getVipCardId());
                                userCoupon.setCouponActivityId(upgradeCouponActivity.getCouponActivityId());
                                userCoupon.setCouponId(couponActivityUpgrade.getCouponId());
                                userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivityUpgrade.getCouponId(), getDate));
                                userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                                userCouponList.add(userCoupon);
                            }
                        }
                        //保存
                        iUserCouponService.batchAddUserCoupon(userCouponList);
                    }
                }
            }
        }

        //2.积豆赠券活动是否开启,结合当前等级当前豆豆是否兑换券,若赠券则扣减可使用豆豆
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(user.getMerchantId(),ActivityTypeEnum.PEA);
        if (null != couponActivity) {
            //查询当前等级对应的兑换条件
            Date peaCouponGetDate = new Date();
            LambdaQueryWrapper<CouponActivityPea> couponActivityPeaLambdaQueryWrapper = new LambdaQueryWrapper<>();
            couponActivityPeaLambdaQueryWrapper.eq(CouponActivityPea::getCouponActivityId, couponActivity.getCouponActivityId());
            couponActivityPeaLambdaQueryWrapper.eq(CouponActivityPea::getLevelId, user.getLevelId());
            List<CouponActivityPea> couponActivityPeaList = iCouponActivityPeaService.list(couponActivityPeaLambdaQueryWrapper);
            if (!couponActivityPeaList.isEmpty()) {
                List<UserCoupon> userCouponList = new ArrayList<>();
                Double needPeaNum = 0d;
                for (CouponActivityPea item : couponActivityPeaList) {
                    if (user.getAvaliablePeaNum() >= item.getNeedPeaNum()) {
                        needPeaNum = item.getNeedPeaNum();
                        for (int i = 0; i < item.getNum(); i++) {
                            UserCoupon userCoupon = new UserCoupon();
                            userCoupon.setUserId(user.getUserId());
                            userCoupon.setVipCardId(user.getVipCardId());
                            userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                            userCoupon.setCouponId(item.getCouponId());
                            userCoupon.setExpireTime(iCouponService.queryExpireDate(item.getCouponId(), peaCouponGetDate));
                            userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                            userCouponList.add(userCoupon);
                        }
                    }
                }
                //保存
                iUserCouponService.batchAddUserCoupon(userCouponList);

                if (!userCouponList.isEmpty()) {
                    //若送券了,则扣减可使用豆豆
                    user.setAvaliablePeaNum(NumberUtil.sub(user.getAvaliablePeaNum(),needPeaNum));
                    updateById(user);
                }
            }
        }

    }

    @Override
    public UserLoginResDTO logout(Long userId) {
        UserLoginResDTO userLoginResDTO = new UserLoginResDTO();
        userLoginResDTO.setToken("");
        return  userLoginResDTO;
    }

    @Override
    public void updateLocation(UpdateLocationDTO updateLocationDTO) {
        User user = getById(updateLocationDTO.getUserId());
        user.setLongitude(updateLocationDTO.getLongitude());
        user.setLatitude(updateLocationDTO.getLatitude());
        updateById(user);
    }

    @Override
    public void modifyUserInfo(ModifyUserInfoDTO modifyUserInfoDTO) {
        User user = getById(modifyUserInfoDTO.getUserId());
        if (null != user.getBirthday() && null != modifyUserInfoDTO.getBirthday()) {
            throw new BusinessException(ResultCodeEnum.USER_BIRTHDAY_MODIFY_LIMIT);
        }else if(null == user.getBirthday() && null != modifyUserInfoDTO.getBirthday()){
            user.setBirthday(modifyUserInfoDTO.getBirthday());
            //设置生日到期事件,到期后给用户赠送优惠券
            String birthdayKey = RedisKeyEnum.USER_BIRTHDAY_EVENT.getKey() + user.getUserId();
            Date birthday = modifyUserInfoDTO.getBirthday();
            Date birthdayDate = DateUtil.offsetMonth(birthday, 12);
            iRedisService.setObj(birthdayKey, user.getUserId(), birthdayDate.getTime() - birthday.getTime());
        }
        BeanUtil.copyProperties(modifyUserInfoDTO,user);
        updateById(user);


    }

    @Override
    public User queryUserByMobile(Long merchantId, String mobile) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getMerchantId, merchantId);
        userLambdaQueryWrapper.eq(User::getMobile, mobile);
        return getOne(userLambdaQueryWrapper);
    }

    @Override
    public QueryHomeDataResDTO queryHomeData(Integer merchantId) {
        return userMapper.queryHomeData(merchantId);
    }


    /**
     * 设置返回数据
     *
     * @param user
     * @param userLoginResDTO
     */
    private void fillData(User user, UserLoginResDTO userLoginResDTO) {
        //已存在,老用户
        BeanUtil.copyProperties(user, userLoginResDTO);
        //重新下发token
        userLoginResDTO.setToken(iTokenService.getToken(user));

        //等级信息
        LambdaQueryWrapper<Level> levelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        levelLambdaQueryWrapper.eq(Level::getMerchantId, user.getMerchantId());
        List<Level> levelList = iLevelService.list(levelLambdaQueryWrapper);
        levelList.sort((level1, level2) -> {
            return level1.getMinValue() - level2.getMinValue();
        });
        Level currentLevel = null;
        Level nextLevel = null;
        for (int i = 0; i < levelList.size(); i++) {
            if (user.getLevelId().equals(levelList.get(i).getLevelId())) {
                currentLevel = levelList.get(i);
                if (i != levelList.size() - 1) {
                    nextLevel = levelList.get(i + 1);
                }
                break;
            }
        }
        if (null != currentLevel) {
         userLoginResDTO.setLevelName(currentLevel.getName());
        }
        if (null == nextLevel) {
            userLoginResDTO.setFullLevelFlag(true);
            userLoginResDTO.setUpgradeTip("满级达成");
        } else {
            userLoginResDTO.setFullLevelFlag(false);
            String tipFormatStr = "再积%s颗可升级为%s";
            userLoginResDTO.setUpgradeTip(String.format(tipFormatStr
                    , NumberUtil.decimalFormat(".#",(currentLevel.getMaxValue() - user.getTotalPeaNum())), nextLevel.getName()));
        }

        //主卡信息
        if (null != user.getVipCardId()) {
            UserVipCard userVipCard = iUserVipCardService.queryUserVipCard(user.getVipCardId());
            userLoginResDTO.setPrimaryCardBalance(Double.parseDouble(NumberUtil.decimalFormat(".##",userVipCard.getBalance())));
            userLoginResDTO.setVipCardNum(iUserVipCardService.queryTotalUserVipCardCount(user.getUserId()));
        }

        //优惠券数量
        userLoginResDTO.setAvaliableCouponNum(iUserCouponService.queryTotalAvailableCouponCount(user.getUserId()));


    }

}
