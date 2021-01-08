package top.yigege.vo.gaode;

import lombok.Data;

/**
 * @ClassName: AddressComponentBean
 * @Description:地址元素列表
 * @author: yigege
 * @date: 2021年01月08日 14:36
 */
@Data
public class AddressComponentBean {

    /**
     * 省
     */
    String province;

    /**
     * 城市 当城市是省直辖县时返回为空，以及城市为北京、上海、天津、重庆四个直辖市时，该字段返回为空
     */
    String city;

    /**
     * 坐标点所在区
     */
    String district;

    /**
     * 城市编码
     */
    String citycode;

    /**
     * 行政区编码
     */
    String adcode;

}
