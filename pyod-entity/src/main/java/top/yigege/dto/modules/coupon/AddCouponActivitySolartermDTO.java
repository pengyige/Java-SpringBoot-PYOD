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
public class AddCouponActivitySolartermDTO {

    /**
     * 活动id
     */
    @NotNull(message = "活动id不能为空")
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    @NotNull(message = "优惠券id不能为空")
    private Long couponId;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Integer num;

}
