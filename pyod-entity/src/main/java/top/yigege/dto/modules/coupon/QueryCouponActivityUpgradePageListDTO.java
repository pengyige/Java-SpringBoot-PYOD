package top.yigege.dto.modules.coupon;

import lombok.Data;

/**
 * @ClassName: QueryCouponActivityUpgradePageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月26日 15:56
 */
@Data
public class QueryCouponActivityUpgradePageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
