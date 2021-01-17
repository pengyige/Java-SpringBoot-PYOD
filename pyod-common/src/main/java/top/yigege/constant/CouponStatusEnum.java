package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: CouponStatusEnum
 * @Description:优惠券状态
 * @author: yigege
 * @date: 2021年01月12日 17:58
 */
@Getter
@AllArgsConstructor
public enum CouponStatusEnum {

    //   * 优惠券状态(1-可使用,2-已使用,3-已赠送未领取,4-赠送成功)
    OUT_OF_DATE(5,"已过期"),

    AVAILABLE(1,"可使用"),

    AREADY_USED(2,"已使用"),

    SEND_UN_PICK(3,"赠送未领取"),

    SEND_SUCCESS(4,"赠送成功");

    Integer code;

    String msg;
}
