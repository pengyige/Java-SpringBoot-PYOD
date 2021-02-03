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
@TableName("t_pic")
public class Pic extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId(value = "pic_id", type = IdType.AUTO)
    private Long picId;

    /**
     * 业务id
     */
    private Long businessId;

    /**
     * 业务类型(1-商铺)
     */
    private Integer businessType;

    /**
     * 封面标识(0-否,1-是)
     */
    private Integer primaryFlag;

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
