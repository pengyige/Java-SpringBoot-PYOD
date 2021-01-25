package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @ClassName: ActivityTypeEnum
 * @Description:活动类型
 * @author: yigege
 * @date: 2021年01月12日 15:16
 */
@Getter
@AllArgsConstructor
public enum ActivityTypeEnum {

        NEW_USER_REGISTER(1,"新用户注册"),

        BUY_PRODUCT(2,"购买商品"),

        CD_KEY(3,"兑换cdkey"),

        FRIEND_GIVE(4,"好友赠送"),

        UPGRADE(5,"升级发放"),

        BIRTHDAY(6,"会员卡生日发放"),

        SOLAR_TERM(7,"节气发放"),

        EXCHNAGE_INTEGRAL(8,"积分兑换"),

        PEA(9,"积豆赠券"),

        FESTIVAL(10,"节日发放");

        Integer code;

         String msg;
}
