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
 * 标签
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_label")
public class Label extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "label_id", type = IdType.AUTO)
    private Integer labelId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(1-商铺,2-职位,3-学历,4-月收入,5-兴趣，6-偏好)
     */
    private Integer type;

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
