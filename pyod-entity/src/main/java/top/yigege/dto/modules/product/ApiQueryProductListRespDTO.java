package top.yigege.dto.modules.product;

import lombok.Data;
import top.yigege.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ApiQueryProductListRespDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年06月08日 11:02
 */
@Data
public class ApiQueryProductListRespDTO {

    /**
     * 购买券包
     */
    List<Product> ticketProductList = new ArrayList<>();


    /**
     * 充值返券
     */
    List<Product> rechargeProductList = new ArrayList<>();

}
