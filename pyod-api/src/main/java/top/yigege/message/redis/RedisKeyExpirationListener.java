package top.yigege.message.redis;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.BirthdayTypeEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.DictCodeEnum;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityBirthday;
import top.yigege.model.SysDict;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.service.ICouponActivityBirthdayService;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponService;
import top.yigege.service.IRedisService;
import top.yigege.service.ISysDictService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    ICouponActivityBirthdayService iCouponActivityBirthdayService;

    @Autowired
    ICouponService iCouponService;


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
            //会员卡生日赠券
            handlerVipBirthdayEvent(Long.valueOf(expiredKey.split(":")[1]),Long.valueOf(expiredKey.split(":")[2]));
        }else if (expiredKey.startsWith(RedisKeyEnum.USER_BIRTHDAY_EVENT.getKey())) {
            //用户生日赠券
            handlerUserBirthdayEvent(Long.valueOf(expiredKey.split(":")[1]));

        }
    }


     private void handlerUserBirthdayEvent(Long userId) {
         log.info("用户id:{},用户生日处理...",userId);
         User user = iUserService.getById(userId);
         Date getDate = new Date();
         CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(user.getMerchantId(),ActivityTypeEnum.BIRTHDAY);
         if (null != couponActivity) {
             //获取活动对应的优惠券
             LambdaQueryWrapper<CouponActivityBirthday> couponActivityBirthdayLambdaQueryWrapper = new LambdaQueryWrapper<>();
             couponActivityBirthdayLambdaQueryWrapper.eq(CouponActivityBirthday::getCouponActivityId,couponActivity.getCouponActivityId());
             couponActivityBirthdayLambdaQueryWrapper.eq(CouponActivityBirthday::getType, BirthdayTypeEnum.USER.getCode());
             List<CouponActivityBirthday> couponActivityBirthdayList = iCouponActivityBirthdayService.list(couponActivityBirthdayLambdaQueryWrapper);
             if (!couponActivityBirthdayList.isEmpty()) {
                 List<UserCoupon> userCouponList = new ArrayList<>();
                 for (CouponActivityBirthday couponActivityBirthday: couponActivityBirthdayList) {
                     for (int i = 0;i < couponActivityBirthday.getNum();i++) {
                         UserCoupon userCoupon = new UserCoupon();
                         userCoupon.setUserId(userId);
                         userCoupon.setVipCardId(user.getVipCardId());
                         userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                         userCoupon.setCouponId(couponActivityBirthday.getCouponId());
                         userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivityBirthday.getCouponId(),getDate));
                         userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                         userCouponList.add(userCoupon);
                     }
                 }

                 //保存
                 iUserCouponService.batchAddUserCoupon(userCouponList);
             }
         }

         //设置生日到期事件,到期后给该卡赠送优惠券
         String birthdayKey = RedisKeyEnum.USER_BIRTHDAY_EVENT.getKey() + userId;
         Date registerDate = getDate;
         Date birthdayDate = DateUtil.offsetMonth(registerDate,12);
         iRedisService.setObj(birthdayKey,userId,birthdayDate.getTime()-registerDate.getTime());
         log.info("用户id:{},vipCardId:{},用户生日处理完成,下一次生日：{}",userId,user.getVipCardId(),birthdayDate);
     }

    /**
     * 会员卡生日
     * @param userId
     */
    private void handlerVipBirthdayEvent(Long userId,Long vipCardId) {
        log.info("用户id:{},vipCardId:{},会员卡生日处理...",userId,vipCardId);
        //获取会员卡生日赠券活动并赠券,并重新设置新的过期时间
        User user = iUserService.getById(userId);
        Date getDate = new Date();
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(user.getMerchantId(),ActivityTypeEnum.BIRTHDAY);
        if (null != couponActivity) {
            //获取活动对应的优惠券
            LambdaQueryWrapper<CouponActivityBirthday> couponActivityBirthdayLambdaQueryWrapper = new LambdaQueryWrapper<>();
            couponActivityBirthdayLambdaQueryWrapper.eq(CouponActivityBirthday::getCouponActivityId,couponActivity.getCouponActivityId());
            couponActivityBirthdayLambdaQueryWrapper.eq(CouponActivityBirthday::getType, BirthdayTypeEnum.VIP_CARD.getCode());
            List<CouponActivityBirthday> couponActivityBirthdayList = iCouponActivityBirthdayService.list(couponActivityBirthdayLambdaQueryWrapper);
            if (!couponActivityBirthdayList.isEmpty()) {
                List<UserCoupon> userCouponList = new ArrayList<>();
                for (CouponActivityBirthday couponActivityBirthday: couponActivityBirthdayList) {
                    for (int i = 0 ; i < couponActivityBirthday.getNum(); i++) {
                        UserCoupon userCoupon = new UserCoupon();
                        userCoupon.setUserId(userId);
                        userCoupon.setVipCardId(vipCardId);
                        userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                        userCoupon.setCouponId(couponActivityBirthday.getCouponId());
                        userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivityBirthday.getCouponId(),getDate));
                        userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                        userCouponList.add(userCoupon);
                    }
                }
                //保存
                iUserCouponService.batchAddUserCoupon(userCouponList);
            }
        }

        //设置生日到期事件,到期后给该卡赠送优惠券
        String birthdayKey = RedisKeyEnum.BIRTHDAY_EVENT.getKey() + userId + ":" + vipCardId;
        Date registerDate = getDate;
        Date birthdayDate = DateUtil.offsetMonth(registerDate,12);
        iRedisService.setObj(birthdayKey,userId,birthdayDate.getTime()-registerDate.getTime());
        log.info("用户id:{},vipCardId:{},生日处理完成,下一次生日：{}",userId,vipCardId,birthdayDate);
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
        user.setAvaliablePeaNum(0d);
        user.setTotalPeaNum(0d);

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
