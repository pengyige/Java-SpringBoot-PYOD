package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @ClassName: RedisKeyEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 17:07
 */
@Getter
@AllArgsConstructor
public enum RedisKeyEnum {

    WEATHER_NATIONAL_PRECIPITATION("weather:national-precipitation","全国降水量"),

    PEA_EXPIRE_EVENT("pea-expire-event:","豆豆失效事件"),

    BIRTHDAY_EVENT("birthday-event:","生日到期事件"),

    GIVE_USER_COUPON_EVENT("give-user-coupon-event:","赠送优惠券失效事件");

    private String key;

    private String msg;


}
