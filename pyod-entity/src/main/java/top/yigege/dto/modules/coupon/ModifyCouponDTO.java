package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: ModifyCouponDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 16:40
 */
@Data
public class ModifyCouponDTO {

    @NotNull(message = "优惠券id不能为空")
    private Long couponId;

    /**
     * 名称
     */
    private String name;

    /**
     * 面额
     */
    private Double price;

    /**
     * 使用描述
     */
    private String useDesc;

    /**
     * 优惠券类型(1-兑换券,2-立减券,3-满减券,4-折扣券,5-随机券)
     */
    private Integer type;

    /**
     * 有效期类型(1-固定时间,2-相对时间)
     */
    private Integer indateType;

    /**
     * 是否互斥(0-不互斥,1-互斥)
     */
    private Integer mutexFlag;

    /**
     * 超过金额(折扣券,满减券使用)
     */
    private String overMoney;

    /**
     * 持续时间(天)
     */
    private Integer duration;

    /**
     * 折扣
     */
    private Double discount;

    /**
     * 最小值(随机券)
     */
    private Double minMoney;

    /**
     * 最大值(随机券)
     */
    private Double maxMoney;

    /**
     * 有效期开始时间
     */
    private Date indateStartTime;

    /**
     * 有效期结束时间
     */
    private Date indateEndTime;
}
