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
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-23
 */
@Data
@TableName("t_cousumption_record")
public class CousumptionRecord extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 消费记录id
     */
    @TableId(value = "consumption_record_id", type = IdType.AUTO)
    private Long consumptionRecordId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 支付会员卡id(现金支付无值)
     */
    private Long vipCardId;

    /**
     * 最终支付金额
     */
    private Double finalPayPrice;

    /**
     * 最终优惠金额
     */
    private Double finalDiscountPrice;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付类型(1-会有卡支付,2-现金支付,3-在线支付,4-扫描支付)
     */
    private Integer payType;

    /**
     * 订单状态(1-已下单,2-已支付)
     */
    private Integer orderStatus;

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
