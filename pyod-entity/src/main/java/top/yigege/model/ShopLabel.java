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
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_shop_label")
public class ShopLabel extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商铺标签id
     */
    @TableId(value = "shop_label_id", type = IdType.AUTO)
    private Long shopLabelId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 标签id
     */
    private Long labelId;

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

    @TableField(exist = false)
    Label label;
}
