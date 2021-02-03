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

        VIP_CARD(1,"会员卡"),

        USER(2,"用户");


        Integer code;

         String msg;
}
