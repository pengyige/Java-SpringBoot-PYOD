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
 * 优惠券cdkey活动
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_coupon_activity_cdkey")
public class CouponActivityCdkey extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券兑换码Id
     */
    @TableId(value = "coupon_activity_cdkey_id", type = IdType.AUTO)
    private Long couponActivityCdkeyId;

    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * cdkey
     */
    private String cdkey;

    /**
     * 兑换用户
     */
    private Long userId;

    /**
     * 优惠券张数
     */
    private Integer num;

    /**
     * 状态(1-已生成,2-已兑换,3-无效)
     */
    private Integer status;

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

}
