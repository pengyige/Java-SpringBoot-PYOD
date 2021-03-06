package top.yigege.model;

import cn.hutool.db.DaoTemplate;
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
 * 优惠券注册活动
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_coupon_activity_register")
public class CouponActivityRegister extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券活动注册id
     */
    @TableId(value = "coupon_activity_register_id", type = IdType.AUTO)
    private Long couponActivityRegisterId;

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
     * 优惠券信息
     */
    @TableField(exist = false)
    private Coupon coupon;

    /**
     * 活动信息
     */
    @TableField(exist = false)
    private CouponActivity couponActivity;

}
