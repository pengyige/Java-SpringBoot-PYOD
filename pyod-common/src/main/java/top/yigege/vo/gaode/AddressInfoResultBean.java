package top.yigege.vo.gaode;

import lombok.Data;

/**
 * @ClassName: AddressInfoResultBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:35
 */
@Data
public class AddressInfoResultBean {

    String status;

    String info;

    String infocode;

    RegeCodeBean regeocode;

}
