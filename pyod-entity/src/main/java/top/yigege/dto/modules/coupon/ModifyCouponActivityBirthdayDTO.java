package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ModifyCouponActivityBirthdayDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 17:05
 */
@Data
public class ModifyCouponActivityBirthdayDTO {

    @NotNull(message = "生日赠券id不能为空")
    private Long couponActivityBirthdayId;

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

    //生日类型 1-用户生日,2-会员卡生日
    private Integer type;

}
