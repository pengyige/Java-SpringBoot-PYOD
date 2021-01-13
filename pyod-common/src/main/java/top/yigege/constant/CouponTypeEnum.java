package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: CouponTypeEnum
 * @Description:优惠券类型
 * @author: yigege
 * @date: 2021年01月12日 15:09
 */
@Getter
@AllArgsConstructor
public enum CouponTypeEnum {

    JYB(1,"兑换券"),

    IMMEDIATELY_REDUCTION(2,"立减券"),

    FULL_REDUCTION(3,"满减券"),

    DISCOUNT(4,"折扣券"),

    RANDOM(5,"随机券");

    private Integer code;

    private String msg;
}
