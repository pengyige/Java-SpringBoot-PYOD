package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityIntegralDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 19:36
 */
@Data
public class ModifyCouponActivityIntegralDTO {

    @NotNull(message = "积分赠券id不能为空")
    private Long couponActivityIntegralId;

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


    private Integer minNeedIntegral;
}
