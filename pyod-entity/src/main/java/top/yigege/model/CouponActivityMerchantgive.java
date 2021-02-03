package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 商家发券
 * </p>
 *
 * @author yigege
 * @since 2021-01-30
 */
@Data
@TableName("t_coupon_activity_merchantgive")
public class CouponActivityMerchantgive extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商家发券id
     */
    @TableId
    private Long couponActivityMerchantgiveId;

    /**
     * 活动id
     */
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券数量
     */
    private Integer num;

    /**
     * 发券范围(1-所有用户,2-指定用户)
     */
    private Integer type;

    /**
     * 用户数量
     */
    private Integer userNum;

    /**
     * 指定用户手机号
     */
    private String mobile;

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

    @TableField(exist = false)
    private Coupon coupon;

}
