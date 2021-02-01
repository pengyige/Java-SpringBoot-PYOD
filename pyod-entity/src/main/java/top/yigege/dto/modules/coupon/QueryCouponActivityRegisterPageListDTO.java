package top.yigege.dto.modules.coupon;

import lombok.Data;

/**
 * @ClassName: QueryCouponActivityRegisterPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月26日 15:11
 */
@Data
public class QueryCouponActivityRegisterPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
