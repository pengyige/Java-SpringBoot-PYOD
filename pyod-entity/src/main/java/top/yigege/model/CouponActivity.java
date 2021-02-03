package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_coupon_activity")
public class CouponActivity extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券活动id
     */
    @TableId(value = "coupon_activity_id", type = IdType.AUTO)
    private Long couponActivityId;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 标题
     */
    private String title;

    /**
     活动类型(1-新用户注册,2-购买商品,3-兑换cdkey,4-好友赠送,5-升级发放,6-生日发放,7-节气发放,8-积分兑换,9-积豆赠券,10-节日发放)     */
    private Integer activityType;

    /**
     * 活动状态(1-正常,2-暂停)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;


}
