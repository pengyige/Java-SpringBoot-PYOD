package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: FestivalTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月22日 21:41
 */
@AllArgsConstructor
@Getter
public enum FestivalTypeEnum {

    ZHONG_QIU(1,"中秋"),

    YUAN_DAN(2,"元旦"),

    SHUANG_SHI_YI(3,"双十一");

    private Integer code;

    private String desc;
}
