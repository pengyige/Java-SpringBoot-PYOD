package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 优惠券抵扣
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_coupon_deduction")
public class CouponDeduction extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券抵扣id
     */
    @TableId(value = "coupon_deduction_id", type = IdType.AUTO)
    private Long couponDeductionId;

    /**
     * 核销流水号
     */
    private String couponDeductionNo;

    /**
     * 商店id
     */
    private Long shopId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户优惠券id
     */
    private Long userCouponId;

    /**
     * 会员卡id
     */
    private Long vipCardId;

    /**
     * 消费记录id
     */
    private Long cousumptionRecordId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 核销状态(1-已核销,2-已退回)
     */
    private Integer status;

    /**
     * 类型(1-核销,2-收款)
     */
    private Integer type;

    /**
     * 收款金额
     */
    private Double gatheringAmount;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private Coupon coupon;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private String userCouponIds;

    @TableField(exist = false)
    private Integer num;



}
