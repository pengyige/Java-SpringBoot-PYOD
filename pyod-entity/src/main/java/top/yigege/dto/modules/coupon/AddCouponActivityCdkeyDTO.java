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
public class AddCouponActivityCdkeyDTO {

    /**
     * 优惠券活动id
     */
    @NotNull(message = "优惠券活动id不能为空")
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    @NotNull(message = "优惠券id不能为空")
    private Long couponId;

    /**
     * 优惠券张数
     */
    @NotNull(message = "优惠券数量")
    private Integer num;

    /**
     * cdkey数量
     */
    @NotNull(message = "cdkey数量不能为空")
    private Integer cdkeyNum;



}
