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
 * 用户优惠券
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_user_coupon")
public class UserCoupon extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户优惠券id
     */
    @TableId(value = "user_coupon_id", type = IdType.AUTO)
    private Long userCouponId;


    /**
     * 活动id
     */
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * vip卡片id
     */
    private Long vipCardId;

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

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 优惠券状态(1-可使用,2-已使用,3-已过期,4-已赠送未领取,5-赠送成功)
     */
    private Integer status;

    @TableField(exist = false)
    String couponName;

    @TableField(exist = false)
    String couponDesc;

    @TableField(exist = false)
    String userCouponIds;

    @TableField(exist = false)
    Integer num;

}
