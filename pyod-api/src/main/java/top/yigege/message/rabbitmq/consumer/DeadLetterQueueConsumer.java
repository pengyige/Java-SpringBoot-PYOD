package top.yigege.message.rabbitmq.consumer;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigege.config.RabbitMQConfig;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DeadLetterQueueConsumer
 * @Description: 死信队列消费者
 * @author: yigege
 * @date: 2021年03月07日 17:09
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {

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

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.DEAD_QUEUEA_GIVE_COUPON_NAME)
    public void receiveGiveCouponMsg(String content,Message message, Channel channel) throws IOException {
        try {
            String msg = content;
            log.info("赠送优惠券死信队列收到消息：{}", msg);
            Long userCouponId = Long.parseLong(msg);
            UserCoupon userCoupon = iUserCouponService.getById(userCouponId);
            //更新状态为可使用
            if (CouponStatusEnum.SEND_UN_PICK.getCode().equals(userCoupon.getStatus())) {
                userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                iUserCouponService.updateById(userCoupon);
                log.info("用户优惠券id:{},赠送优惠券未领取处更新为可使用成功",userCouponId);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e) {
            log.error("赠送优惠券死信队列【{}】,处理失败",content);
            log.error(e.getMessage(), e);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

    @RabbitListener(queues = RabbitMQConfig.DEAD_QUEUEA_PEA_CLEAR_NAME)
    public void receivePeaClearMsg(String content,Message message, Channel channel) throws IOException {
        String msg = content;
        try {
        log.info("豆豆清空死信队列收到消息：{}", msg);
        Long userId = Long.parseLong(msg);

        User user = iUserService.getById(userId);
        if (null == user) {
            log.error("用户ID:{}不存在", msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }
        //重置
        user.setAvaliablePeaNum(0d);
        user.setTotalPeaNum(0d);

        //设置豆豆过期时间
        SysDict sysDict = iSysDictService.queryDictByCode(DictCodeEnum.COIN_CLEAR_DURATION_TIME.getCode());
        if (null != sysDict && StringUtils.isNotBlank(sysDict.getCode())) {
            long expireTime = System.currentTimeMillis() + Long.parseLong(sysDict.getValue())*1000;
            user.setExpireTime(new Date(expireTime));
            /*//保存到redis,通过失效key事件处理
            String key = RedisKeyEnum.PEA_EXPIRE_EVENT.getKey() + user.getUserId();
            iRedisService.setObj(key,user.getUserId(),Long.parseLong(sysDict.getValue()));*/
        }
        //设置豆豆过期消息
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME,RabbitMQConfig.DELAY_QUEUEA_PEA_CLAER_ROUTING_KEY,userId+"");
        iUserService.updateById(user);
        log.info("用户id:{},豆豆到期豆豆清理处理成功,可用豆豆:{},累计豆豆:{},到期时间:{}",
                userId,
                user.getAvaliablePeaNum(),
                user.getTotalPeaNum(),
                user.getExpireTime());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e) {
            log.error("豆豆到期死信队列【{}】,处理失败",content);
            log.error(e.getMessage(), e);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.DEAD_QUEUEA_USER_BIRTHDAY_NAME)
    public void receiveUserBirthdayMsg(String content,Message message, Channel channel) throws IOException {
        String msg = content;
        try {
            log.info("用户生日死信队列收到消息：{}", msg);
            Long userId = Long.parseLong(msg);
            User user = iUserService.getById(userId);
            if (null == user) {
                log.error("用户ID:{}不存在", msg);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                return;
            }
            Date getDate = new Date();
            CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(user.getMerchantId(), ActivityTypeEnum.BIRTHDAY);
            if (null != couponActivity) {
                //获取活动对应的优惠券
                LambdaQueryWrapper<CouponActivityBirthday> couponActivityBirthdayLambdaQueryWrapper = new LambdaQueryWrapper<>();
                couponActivityBirthdayLambdaQueryWrapper.eq(CouponActivityBirthday::getCouponActivityId, couponActivity.getCouponActivityId());
                couponActivityBirthdayLambdaQueryWrapper.eq(CouponActivityBirthday::getType, BirthdayTypeEnum.USER.getCode());
                List<CouponActivityBirthday> couponActivityBirthdayList = iCouponActivityBirthdayService.list(couponActivityBirthdayLambdaQueryWrapper);
                if (!couponActivityBirthdayList.isEmpty()) {
                    List<UserCoupon> userCouponList = new ArrayList<>();
                    for (CouponActivityBirthday couponActivityBirthday : couponActivityBirthdayList) {
                        for (int i = 0; i < couponActivityBirthday.getNum(); i++) {
                            UserCoupon userCoupon = new UserCoupon();
                            userCoupon.setUserId(userId);
                            userCoupon.setVipCardId(user.getVipCardId());
                            userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                            userCoupon.setCouponId(couponActivityBirthday.getCouponId());
                            userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivityBirthday.getCouponId(), getDate));
                            userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                            userCouponList.add(userCoupon);
                        }
                    }

                    //保存
                    iUserCouponService.batchAddUserCoupon(userCouponList);
                }
            }

            //设置生日到期消息
            rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_QUEUEA_USER_BIRTHDAY_ROUTING_KEY, userId + "");
            log.info("用户id:{},vipCardId:{},用户生日处理完成", userId, user.getVipCardId());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e) {
            log.error("用户生日死信队列【{}】,处理失败",content);
            log.error(e.getMessage(), e);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
