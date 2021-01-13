package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: BusinessType
 * @Description:业务类型
 * @author: yigege
 * @date: 2021年01月12日 17:33
 */
@Getter
@AllArgsConstructor
public enum BusinessType {

    SHOP(1,"商铺");

    Integer code;

    String msg;
}
