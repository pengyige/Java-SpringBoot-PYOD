package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: CdKeyStatusEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月12日 15:28
 */
@Getter
@AllArgsConstructor
public enum CdKeyStatusEnum {

    NEW(1,"已生成"),

    ALREADY_USED(2,"已使用"),

    INVALID(3,"无效");//人工设置

    Integer code;

    String msg;
}
