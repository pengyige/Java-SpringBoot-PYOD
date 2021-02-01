package top.yigege.dto.modules.coupon;

import lombok.Data;

/**
 * @ClassName: QueryCouponPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 16:44
 */
@Data
public class QueryCouponPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
