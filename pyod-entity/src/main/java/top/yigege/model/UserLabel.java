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
 * 用户标签
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_user_label")
public class UserLabel extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标签id
     */
    @TableId(value = "user_label_id", type = IdType.AUTO)
    private Long userLabelId;

    /**
     * 用户id
     */
    private Long userId;

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


}
