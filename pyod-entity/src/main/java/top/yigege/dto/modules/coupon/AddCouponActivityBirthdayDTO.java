package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityBirthdayDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 17:03
 */
@Data
public class AddCouponActivityBirthdayDTO {


    /**
     * 活动id
     */
    @NotNull(message = "活动id不能为空")
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    @NotNull(message = "优惠券id不能为空")
    private Long couponId;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Integer num;

    //生日类型 1-用户生日,2-会员卡生日
    @NotNull(message = "类型不能为空")
    private Integer type;

}
