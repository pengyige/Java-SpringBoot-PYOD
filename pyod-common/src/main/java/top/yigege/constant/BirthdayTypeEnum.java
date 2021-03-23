package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: BirthdayTypeEnum
 * @Description:生日类型
 * @author: yigege
 * @date: 2021年01月12日 15:16
 */
@Getter
@AllArgsConstructor
public enum BirthdayTypeEnum {


    USER(1,"用户"),

    VIP_CARD(2,"会员卡");


    Integer code;

         String msg;
}
