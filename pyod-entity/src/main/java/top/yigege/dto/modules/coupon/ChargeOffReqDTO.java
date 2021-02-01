package top.yigege.dto.modules.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ChargeOffReqDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月31日 14:03
 */
@Data
public class ChargeOffReqDTO {


    @ApiModelProperty(value = "店铺id不能为空",required = true)
    @NotNull(message = "店铺id不能为空")
    Long shopId;

    @ApiModelProperty(value = "用户优惠券id",required = true)
    @NotNull(message = "用户优惠券id不能为空")
    Long userCouponId;

    @ApiModelProperty(value = "使用数量",required = true)
    @NotNull(message = "数量不能为空")
    Integer num;


}
