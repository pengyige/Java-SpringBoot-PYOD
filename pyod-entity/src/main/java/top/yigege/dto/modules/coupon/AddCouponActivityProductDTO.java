package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityProductDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月26日 15:24
 */
@Data
public class AddCouponActivityProductDTO {

    /**
     * 优惠券活动id
     */
    @NotNull(message = "优惠券活动id不能为空")
    private Long couponActivityId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Long productId;

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
