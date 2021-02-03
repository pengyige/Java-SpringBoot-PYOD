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
@TableName("t_level_welfare")
public class LevelWelfare extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 等级福利id
     */
    @TableId(value = "level_welfare_id", type = IdType.AUTO)
    private Long levelWelfareId;

    /**
     * 等级id
     */
    private Long levelId;

    /**
     * 福利id
     */
    private Long welfareId;

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
    Level level;

    @TableField(exist = false)
    Welfare welfare;

}
