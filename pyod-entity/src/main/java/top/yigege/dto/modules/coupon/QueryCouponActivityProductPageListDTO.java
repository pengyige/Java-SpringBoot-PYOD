package top.yigege.dto.modules.coupon;

import lombok.Data;

/**
 * @ClassName: QueryCouponActivityProductPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月26日 15:30
 */
@Data
public class QueryCouponActivityProductPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
