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
public class ModifyCouponActivityProductDTO {

    @NotNull(message = "赠券id不能为空")
    private Long couponActivityProductId;

    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券数量
     */
    private Integer num;


}
