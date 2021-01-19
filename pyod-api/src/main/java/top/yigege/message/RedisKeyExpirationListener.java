package top.yigege.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.DictCodeEnum;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.model.SysDict;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.service.IRedisService;
import top.yigege.service.ISysDictService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;

import java.util.Date;

/**
 * @ClassName: RedisKeyExpirationListener
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月13日 13:40
 */
@Component
@Slf4j
public class RedisKeyExpirationListener  extends KeyExpirationEventMessageListener {

    @Autowired
    ISysDictService iSysDictService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IRedisService iRedisService;

    @Autowired
    IUserCouponService iUserCouponService;


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        log.info(expiredKey);
        if (expiredKey.startsWith(RedisKeyEnum.PEA_EXPIRE_EVENT.getKey())) {
            //处理用户豆豆清空
            handlerPeaExpireEvent(Long.valueOf(expiredKey.split(":")[1]));
        }else if (expiredKey.startsWith(RedisKeyEnum.GIVE_USER_COUPON_EVENT.getKey())) {
            //处理用户赠送优惠券未领取
            handlerGiveUserCouponUnPickEvent(Long.valueOf(expiredKey.split(":")[1]));
        }else if (expiredKey.startsWith(RedisKeyEnum.BIRTHDAY_EVENT.getKey())) {
            //处理用户生日赠券
            handlerUserBirthdayEvent(Long.valueOf(expiredKey.split(":")[1]));
        }
    }

    /**
     * 用户生日
     * @param userId
     */
    private void handlerUserBirthdayEvent(Long userId) {
        log.info("用户id:{},生日处理...",userId);
        //TODO 获取用户生日赠券活动并赠券,并重新设置新的过期时间
    }

    /**
     * 处理赠送用户优惠券事件
     * @param userCouponId
     */
    private void handlerGiveUserCouponUnPickEvent(Long userCouponId) {
        log.info("用户优惠券id:{},赠送优惠券未领取处理...",userCouponId);
        UserCoupon userCoupon = iUserCouponService.getById(userCouponId);
        //更新状态为可使用
        if (CouponStatusEnum.SEND_UN_PICK.getCode().equals(userCoupon.getStatus())) {
            userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
            iUserCouponService.updateById(userCoupon);
            log.info("用户优惠券id:{},赠送优惠券未领取处更新为可使用成功",userCouponId);
        }

    }

    /**
     * 处理用户豆豆过期
     * @param userId
     */
    private void handlerPeaExpireEvent(Long userId) {
        log.info("用户id:{},豆豆到期豆豆清理处理...",userId);
        User user = iUserService.getById(userId);
        //重置
        user.setAvaliablePeaNum(0);
        user.setTotalPeaNum(0);

        //设置豆豆过期时间
        SysDict sysDict = iSysDictService.queryDictByCode(DictCodeEnum.COIN_CLEAR_DURATION_TIME.getCode());
        if (null != sysDict && StringUtils.isNotBlank(sysDict.getCode())) {
            long expireTime = System.currentTimeMillis() + Long.parseLong(sysDict.getValue())*1000;
            user.setExpireTime(new Date(expireTime));
            //保存到redis,通过失效key事件处理
            String key = RedisKeyEnum.PEA_EXPIRE_EVENT.getKey() + user.getUserId();
            iRedisService.setObj(key,user.getUserId(),Long.parseLong(sysDict.getValue()));
        }
        iUserService.updateById(user);
        log.info("用户id:{},豆豆到期豆豆清理处理成功,可用豆豆:{},累计豆豆:{},到期时间:{}",
                userId,
                user.getAvaliablePeaNum(),
                user.getTotalPeaNum(),
                user.getExpireTime());
    }
}
