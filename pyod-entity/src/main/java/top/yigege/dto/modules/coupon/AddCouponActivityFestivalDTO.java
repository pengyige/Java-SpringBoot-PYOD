package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityFestivalDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月30日 14:12
 */
@Data
public class AddCouponActivityFestivalDTO {

    /**
     * 活动id
     */
    @NotNull(message = "活动id不能为空")
    private Long couponActivityId;

    /**
     * 节日类型(1-中秋,2-元旦,3-双十一)
     */
    @NotNull(message = "节日类型不能为空")
    private Integer type;

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
}
