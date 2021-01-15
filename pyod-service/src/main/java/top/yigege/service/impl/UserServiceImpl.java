package top.yigege.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yigege.config.WxConfig;
import top.yigege.constant.DictCodeEnum;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dao.UserMapper;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserLoginResDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.Label;
import top.yigege.model.Level;
import top.yigege.model.SysDict;
import top.yigege.model.User;
import top.yigege.model.UserVipCard;
import top.yigege.model.VipCard;
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

import java.util.Date;
import java.util.List;

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

    @Autowired
    WxConfig wxConfig;

    @Autowired
    WeixinUtil weixinUtil;

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
    IUserCouponService iUserCouponService;

    @Override
    public UserLoginResDTO loginByCode(String code) {
        Code2SessionResultBean code2SessionResultBean = weixinUtil.getCode2Session(code);
        if (0 != code2SessionResultBean.getErrcode()) {
            throw new BusinessException(ResultCodeEnum.CALL_WEIXIN_API_ERROR.getCode(),
                    code2SessionResultBean.getErrcode() + "-" + code2SessionResultBean.getErrmsg());
        }

        //根据openId判断用户是否已存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getOpenid, code2SessionResultBean.getOpenid());

        User user = getOne(userLambdaQueryWrapper);
        UserLoginResDTO userLoginResDTO = new UserLoginResDTO();
        if (null != user) {
            fillData(user, userLoginResDTO);
        } else {

            userLoginResDTO.setOpenid(code2SessionResultBean.getOpenid());
            userLoginResDTO.setToken("");
            userLoginResDTO.setMobile("");
        }
        //将openid对应的session_key缓存到redis
        iRedisService.setObj(code2SessionResultBean.getOpenid(), code2SessionResultBean.getSession_key(), 300);

        return userLoginResDTO;
    }


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

        //根据openId判断用户是否已存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getOpenid, userLoginDetailReqDTO.getOpenid());

        User user = getOne(userLambdaQueryWrapper);
        UserLoginResDTO userLoginResDTO = new UserLoginResDTO();
        if (null != user) {
            fillData(user, userLoginResDTO);
        } else {
            User newUser = new User();
            newUser.setNickname(wxUserInfoDataBean.getNickName());
            newUser.setAvatar(wxUserInfoDataBean.getAvatarUrl());
            newUser.setSex(wxUserInfoDataBean.getGender());
            newUser.setOpenid(userLoginDetailReqDTO.getOpenid());

            //初始等级
            Level defaultLevel  = iLevelService.queryDefaultLevel();
            newUser.setLevelId(defaultLevel.getLevelId());

            //设置豆豆过期时间
            String key = "";
            SysDict sysDict = iSysDictService.queryDictByCode(DictCodeEnum.COIN_CLEAR_DURATION_TIME.getCode());
            if (null != sysDict && StringUtils.isNotBlank(sysDict.getCode())) {
                long expireTime = System.currentTimeMillis() + Long.parseLong(sysDict.getValue())*1000;
                newUser.setExpireTime(new Date(expireTime));
            }
            save(newUser);
            if (null != newUser.getExpireTime()) {
                //保存到redis,通过失效key事件处理
                key = RedisKeyEnum.PEA_EXPIRE_EVENT.getKey() + newUser.getUserId();
                iRedisService.setObj(key,newUser.getUserId(),Long.parseLong(sysDict.getValue()));
            }

            BeanUtil.copyProperties(newUser, userLoginResDTO);
            userLoginResDTO.setToken(iTokenService.getToken(newUser));
            userLoginResDTO.setLevelName(defaultLevel.getName());
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
        //根据openId判断用户是否已存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getOpenid, bindWxUserMobileReqDTO.getOpenid());

        User user = getOne(userLambdaQueryWrapper);
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


    /**
     * 设置返回数据
     * @param user
     * @param userLoginResDTO
     */
    private void fillData(User user, UserLoginResDTO userLoginResDTO) {
        //已存在,老用户
        BeanUtil.copyProperties(user, userLoginResDTO);
        //重新下发token
        userLoginResDTO.setToken(iTokenService.getToken(user));

        //等级信息
        List<Level> levelList = iLevelService.list();
        levelList.sort((level1,level2) -> {
            return level1.getMinValue() - level2.getMinValue();
        });
        Level currentLevel = null;
        Level nextLevel = null;
        for (int i = 0 ; i < levelList.size(); i++) {
            if (user.getLevelId().equals(levelList.get(i).getLevelId())) {
                currentLevel = levelList.get(i);
                if (i != levelList.size()-1) {
                    nextLevel = levelList.get(i+1);
                }
                break;
            }
        }
        userLoginResDTO.setLevelName(currentLevel.getName());

        if (null == nextLevel) {
            userLoginResDTO.setFullLevelFlag(true);
            userLoginResDTO.setUpgradeTip("满级达成");
        }else {
            userLoginResDTO.setFullLevelFlag(false);
            String tipFormatStr = "再积%d颗可升级为%s";
            userLoginResDTO.setUpgradeTip(String.format(tipFormatStr
                    ,(currentLevel.getMaxValue()-user.getTotalPeaNum()),nextLevel.getName()));
        }

        //主卡信息
        if (null != user.getVipCardId()) {
            UserVipCard userVipCard = iUserVipCardService.queryUserVipCard(user.getVipCardId());
            userLoginResDTO.setPrimaryCardBalance(userVipCard.getBalance());
            userLoginResDTO.setVipCardNum(iUserVipCardService.queryTotalUserVipCardCount(user.getUserId()));
        }

        //优惠券数量
        userLoginResDTO.setAvaliableCouponNum(iUserVipCardService.queryTotalUserVipCardCount(user.getUserId()));
    }

}
