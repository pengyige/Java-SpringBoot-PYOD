package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: CityTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月11日 15:34
 */
@Getter
@AllArgsConstructor
public enum CityTypeEnum {

    COUNTRY(0,"国家"),

    PROVINCE(1,"省"),

    CITY(2,"市"),

    COUNTY(3,"县");

    private Integer code;

    private String desc;

}
