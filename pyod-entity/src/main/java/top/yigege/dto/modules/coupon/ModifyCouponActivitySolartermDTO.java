package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivitySolartermDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 18:07
 */
@Data
public class ModifyCouponActivitySolartermDTO {

    @NotNull(message = "节气赠券id不能为空")
    private Long couponActivitySolartermId;

    /**
     * 活动id
     */
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 数量
     */
    private Integer num;

}
