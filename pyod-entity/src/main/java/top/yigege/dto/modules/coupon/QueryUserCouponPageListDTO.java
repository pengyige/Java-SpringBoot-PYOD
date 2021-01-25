package top.yigege.dto.modules.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: QueryUserCouponPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月23日 17:07
 */
@Data
public class QueryUserCouponPageListDTO {

    int page = 1;

    int pageSize = 10;

    @ApiModelProperty(value = "优惠券状态(1-可使用,2-已使用,5-已过期)",required = true)
    @NotNull(message = "优惠券状态不能为空")
    Integer couponStatus;

    @ApiModelProperty(value = "会员卡ID",required = true)
    @NotNull(message = "会员卡id不能为空")
    Long vipCardId;

    Long userId;
}
