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
public class ModifyCouponActivityFestivalDTO {

    @NotNull(message = "节日赠券id不能为空")
    private Long couponActivityFestivalId;

    /**
     * 活动id
     */
    private Long couponActivityId;

    /**
     * 节日类型(1-中秋,2-元旦,3-双十一)
     */
    private Integer type;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 数量
     */
    private Integer num;
}
