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
 * 优惠券商品活动
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_coupon_activity_product")
public class CouponActivityProduct extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券商品id
     */
    @TableId(value = "coupon_activity_product_id", type = IdType.AUTO)
    private Long couponActivityProductId;

    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券数量
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

}
