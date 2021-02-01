package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityCdkeyDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 12:09
 */
@Data
public class ModifyCouponActivityCdkeyDTO {

    @NotNull(message = "cdkeyid不能为空")
    private Long couponActivityCdkeyId;

    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券张数
     */
    private Integer num;


}
