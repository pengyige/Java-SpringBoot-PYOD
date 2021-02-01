package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: QueryCouponActivityPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月25日 18:14
 */
@Data
public class QueryCouponActivityPageListDTO {

    int page = 1;

    int pageSize =10;

    Long merchantId;
}
