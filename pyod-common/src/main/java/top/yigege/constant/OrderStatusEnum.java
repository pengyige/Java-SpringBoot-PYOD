package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: OrderStatusEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月17日 16:28
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {

    ALREADY_ORDERS(1,"已下单"),

    ALREADY_PAY(2,"已支付");

    Integer code;

    String msg;

}
