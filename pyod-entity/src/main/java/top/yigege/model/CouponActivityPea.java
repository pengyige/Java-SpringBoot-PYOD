package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 优惠券积豆赠券活动
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
@Data
@TableName("t_coupon_activity_pea")
public class CouponActivityPea extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券积豆活动id
     */
    @TableId(value = "coupon_activity_pea_id", type = IdType.AUTO)
    private Long couponActivityPeaId;

    /**
     * 优惠券活动id
     */
    private Long couponActivityId;

    /**
     * 等级id
     */
    private Long levelId;

    /**
     * 需要多少豆豆兑换
     */
    private Double needPeaNum;

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

}
