package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ProductTypeEnum
 * @Description:商品类型
 * @author: yigege
 * @date: 2021年01月12日 17:40
 */
@Getter
@AllArgsConstructor
public enum ProductTypeEnum {

    BUG_TICKET(1,"购买券包"),

    RECHARGE(2,"充值返券");

    Integer code;

    String msg;
}
