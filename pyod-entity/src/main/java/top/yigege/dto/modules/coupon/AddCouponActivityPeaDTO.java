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
public class AddCouponActivityPeaDTO {

    /**
     * 优惠券活动id
     */
    @NotNull(message = "优惠券活动id不能为空")
    private Long couponActivityId;

    /**
     * 等级id
     */
    @NotNull(message = "等级id不能为空")
    private Long levelId;

    /**
     * 需要多少豆豆兑换
     */
    @NotNull(message = "豆豆数不能为空")
    private Double needPeaNum;

    /**
     * 优惠券id
     */
    @NotNull(message = "优惠券id不能为空")
    private Long couponId;

    /**
     * 优惠券数量
     */
    @NotNull(message = "优惠券数量不能为空")
    private Integer num;
}
