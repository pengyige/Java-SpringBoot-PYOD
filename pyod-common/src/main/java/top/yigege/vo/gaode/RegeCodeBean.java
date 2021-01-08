package top.yigege.vo.gaode;

import lombok.Data;

/**
 * @ClassName: RegeCodeBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:36
 */
@Data
public class RegeCodeBean {

    AddressComponentBean addressComponent;

    String formatted_address;
}
