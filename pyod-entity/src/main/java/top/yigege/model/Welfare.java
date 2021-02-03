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
@TableName("t_welfare")
public class Welfare extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 福利id
     */
    @TableId(value = "welfare_id", type = IdType.AUTO)
    private Long welfareId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 福利名称
     */
    private String name;

    /**
     * 福利图片
     */
    private String imageUrl;

    /**
     * 描述
     */
    @TableField("`desc`")
    private String desc;

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
