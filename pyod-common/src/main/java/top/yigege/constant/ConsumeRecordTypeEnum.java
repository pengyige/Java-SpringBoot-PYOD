package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ConsumeRecordTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月10日 11:34
 */
@Getter
@AllArgsConstructor
public enum ConsumeRecordTypeEnum {

    CHARGE(1,"核销"),

    GATHERING(2,"收款");

    private Integer code;

    private String msg;
}
