package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2020-11-02
 */
@Data
@TableName("t_sys_dict")
@Api(tags = "系统字典")
public class SysDict extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @ApiModelProperty("字典ID")
    @TableId
    private Integer dictId;

    /**
     * 字典码
     */
    @ApiModelProperty("字典码")
    private String code;

    /**
     * 字典值
     */
    @ApiModelProperty("字典值")
    private String value;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
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

}
