package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_product")
public class Product extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 商品类型(1-购买券包，2-充值返券)
     */
    private Integer productType;

    /**
     * 使用描述
     */
    private String useDesc;

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
     * 优惠券列表
     */
    @TableField(exist = false)
    private List<Coupon> couponList = new ArrayList();

}
