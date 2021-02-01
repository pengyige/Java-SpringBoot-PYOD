package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCouponActivityMerchantgiveDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月30日 15:16
 */
@Data
public class AddCouponActivityMerchantgiveDTO {

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
     * 优惠券数量
     */
    @NotNull(message = "优惠券数量不能为空")
    private Integer num;

    /**
     * 发券范围(1-所有用户,2-指定用户)
     */
    @NotNull(message = "发券范围不能为空")
    private Integer type;

    /**
     * 用户数量
     */
    private Integer userNum;

    /**
     * 指定用户手机号
     */
    private String mobile;
}
