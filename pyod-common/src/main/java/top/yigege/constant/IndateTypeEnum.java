package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: IndateTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月16日 16:28
 */
@AllArgsConstructor
@Getter
public enum IndateTypeEnum {

    FIX(1,"固定时间"),

    RELATIVE(2,"相对时间");


    Integer code;

    String msg;
}
