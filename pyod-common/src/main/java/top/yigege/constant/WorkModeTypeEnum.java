package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: WorkModeTypeEnum
 * @Description:工作模式
 * @author: yigege
 * @date: 2021年01月12日 17:47
 */
@Getter
@AllArgsConstructor
public enum WorkModeTypeEnum {

    //     * 工作模式(0-周一至周日,1-周一至周五,2-周一至周六,3-周末)
    MON_TO_SUN(0,"周一至周日"),

    MON_TO_FRI(1,"周一至周五"),

    MON_TO_STA(2,"周一至周六"),

    WEEKEND(3,"周末");

    Integer code;

    String msg;
}
