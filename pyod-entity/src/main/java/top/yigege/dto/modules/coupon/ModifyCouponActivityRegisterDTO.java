package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityRegisterDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月26日 15:06
 */
@Data
public class ModifyCouponActivityRegisterDTO {

    @NotNull(message = "优惠券注册赠券id不能为空")
    private Long couponActivityRegisterId;

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
