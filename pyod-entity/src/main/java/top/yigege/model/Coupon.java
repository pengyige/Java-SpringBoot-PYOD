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
 * 优惠券
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@TableName("t_coupon")
@Data
public class Coupon extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Long couponId;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 名称
     */
    private String name;

    /**
     * 面额
     */
    private Double price;

    /**
     * 使用描述
     */
    private String useDesc;

    /**
     * 优惠券类型(1-兑换券,2-立减券,3-满减券,4-折扣券,5-随机券)
     */
    private Integer type;

    /**
     * 有效期类型(1-固定时间,2-相对时间)
     */
    private Integer indateType;

    /**
     * 是否互斥(0-不互斥,1-互斥)
     */
    private Integer mutexFlag;

    /**
     * 超过金额(折扣券,满减券使用)
     */
    private String overMoney;

    /**
     * 持续时间(天)
     */
    private Integer duration;

    /**
     * 折扣
     */
    private Double discount;

    /**
     * 最小值(随机券)
     */
    private Double minMoney;

    /**
     * 最大值(随机券)
     */
    private Double maxMoney;

    /**
     * 有效期开始时间
     */
    private Date indateStartTime;

    /**
     * 有效期结束时间
     */
    private Date indateEndTime;


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
     * 赠送的优惠券数量
     */
    @TableField(exist = false)
    Integer num;
}
