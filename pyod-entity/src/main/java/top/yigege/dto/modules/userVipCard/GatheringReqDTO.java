package top.yigege.dto.modules.userVipCard;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: GatheringReqDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月10日 10:41
 */
@Data
public class GatheringReqDTO {

    /**
     * 商品ID
     */
    @NotNull(message = "商铺ID不能为空")
    private Long shopId;

    /**
     * vip卡片id
     */
    @NotNull(message = "会员卡ID不能为空")
    private Long vipCardId;

    /**
     * 金额
     */
    @NotNull(message = "支付金额不能为空")
    private Double amount;
}
