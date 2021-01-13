package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: LabelTypeEnum
 * @Description:标签类型
 * @author: yigege
 * @date: 2021年01月12日 17:25
 */
@AllArgsConstructor
@Getter
public enum LabelTypeEnum {

    //   * 类型(1-商铺,2-职位,3-学历,4-月收入,5-兴趣，6-偏好)
    SHOP(1,"商铺"),

    POST(2,"职位"),

    EDUCATION(3,"学历"),

    INCOME(4,"月收入"),

    INTEREST(5, "兴趣"),

    PREFERENCE(6,"偏好");


    Integer code;

    String msg;
}
