package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityUpgradeDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月26日 15:42
 */
@Data
public class ModifyCouponActivityUpgradeDTO {

    /**
     * 升级赠券id
     */
    @NotNull(message = "升级赠券id不能为空")
    private Long couponActivityUpgradeId;


    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 等级id
     */
    private Long levelId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券数量
     */
    private Integer num;

}
