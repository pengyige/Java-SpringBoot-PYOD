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
public class AddCouponActivityIntegralDTO {


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
    @NotNull(message = "优惠券数量不能为空")
    private Integer num;


    @NotNull(message = "积分数不能为空")
    private Integer minNeedIntegral;
}
