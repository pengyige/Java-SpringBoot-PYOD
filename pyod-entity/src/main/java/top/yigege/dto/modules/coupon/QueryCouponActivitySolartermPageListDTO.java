package top.yigege.dto.modules.coupon;

import lombok.Data;

/**
 * @ClassName: QueryCouponActivitySolartermPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 17:07
 */
@Data
public class QueryCouponActivitySolartermPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
