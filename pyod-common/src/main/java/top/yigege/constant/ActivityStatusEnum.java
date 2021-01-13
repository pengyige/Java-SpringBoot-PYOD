package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ActivityStatusEnum
 * @Description:活动状态
 * @author: yigege
 * @date: 2021年01月12日 19:49
 */
@Getter
@AllArgsConstructor
public enum ActivityStatusEnum {

    NORMAL(1,"正常"),

    STOP(2,"暂停");

    Integer code;

    String msg;
}
