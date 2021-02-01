package top.yigege.dto.modules.coupon;

import lombok.Data;

/**
 * @ClassName: QueryCouponActivityIntegralPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 19:39
 */
@Data
public class QueryCouponActivityIntegralPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
