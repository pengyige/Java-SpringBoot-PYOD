package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: RangeTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月12日 15:28
 */
@Getter
@AllArgsConstructor
public enum RangeTypeEnum {

    ALL(1,"所有用户"),

    SINGLE(2,"指定用户");


    Integer code;

    String msg;
}
