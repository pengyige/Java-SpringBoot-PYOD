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
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dao.UserMapper;
import top.yigege.dto.modules.user.BindWxUserMobileReqDTO;
import top.yigege.dto.modules.user.UserLoginDetailReqDTO;
import top.yigege.dto.modules.user.UserLoginResDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.SysDict;
import top.yigege.model.User;
import top.yigege.service.IRedisService;
import top.yigege.service.ISysDictService;
import top.yigege.service.ITokenService;
import top.yigege.service.IUserService;
import top.yigege.util.AEScbcUtil;
import top.yigege.util.WeixinUtil;
import top.yigege.vo.wx.Code2SessionResultBean;
import top.yigege.vo.wx.WxMobileDataBean;
import top.yigege.vo.wx.WxUserInfoDataBean;

import java.util.Date;

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
            //已存在,老用户
            BeanUtil.copyProperties(user, userLoginResDTO);
            //重新下发token
            userLoginResDTO.setToken(iTokenService.getToken(user));

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
            //已存在,老用户
            BeanUtil.copyProperties(user, userLoginResDTO);
            //重新下发token
            userLoginResDTO.setToken(iTokenService.getToken(user));
        } else {
            User newUser = new User();
            newUser.setNickname(wxUserInfoDataBean.getNickName());
            newUser.setAvatar(wxUserInfoDataBean.getAvatarUrl());
            newUser.setSex(wxUserInfoDataBean.getGender() + 1);
            newUser.setOpenid(userLoginDetailReqDTO.getOpenid());

            //设置豆豆过期时间
            SysDict sysDict = iSysDictService.queryDictByCode(DictCodeEnum.COIN_CLEAR_DURATION_TIME.getCode());
            if (null != sysDict && StringUtils.isNotBlank(sysDict.getCode())) {
                long expireTime = System.currentTimeMillis() + Long.parseLong(sysDict.getValue());
                newUser.setExpireTime(new Date(expireTime));

            }

            save(newUser);
            BeanUtil.copyProperties(newUser, userLoginResDTO);
            userLoginResDTO.setToken(iTokenService.getToken(newUser));
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
            BeanUtil.copyProperties(user, userLoginResDTO);
        }

        return userLoginResDTO;
    }
}
