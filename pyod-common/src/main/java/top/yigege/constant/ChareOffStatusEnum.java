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
public enum ChareOffStatusEnum {

    FINISH(1,"已核销"),

    BACK(2,"已退回");

    Integer code;

    String msg;
}
