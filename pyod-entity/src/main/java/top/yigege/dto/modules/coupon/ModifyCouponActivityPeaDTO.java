package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityPeaDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月30日 10:52
 */
@Data
public class ModifyCouponActivityPeaDTO {

    @NotNull(message = "积豆赠券id不能为空")
    private Long couponActivityPeaId;
    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 等级id
     */
    private Long levelId;

    /**
     * 需要多少豆豆兑换
     */
    private Double needPeaNum;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券数量
     */
    private Integer num;
}
