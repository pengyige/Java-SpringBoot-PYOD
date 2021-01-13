package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: YesOrNo
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月11日 16:34
 */
@Getter
@AllArgsConstructor
public enum YesOrNo {

    NO(0,"没有"),

    YES(1,"有");

    private Integer code;

    private String desc;
}
