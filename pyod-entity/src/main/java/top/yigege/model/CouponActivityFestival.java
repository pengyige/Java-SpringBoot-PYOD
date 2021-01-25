package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 优惠券注册活动
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
@Data
@TableName("t_coupon_activity_festival")
public class CouponActivityFestival extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券活动节日id
     */
    @TableId(value = "coupon_activity_register_id", type = IdType.AUTO)
    private Long couponActivityRegisterId;

    /**
     * 活动id
     */
    private Long couponActivityId;

    /**
     * 节日类型(1-中秋,2-元旦,3-双十一)
     */
    private Integer type;

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
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
