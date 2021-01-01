package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2020-10-14
 */
@ApiModel("菜单信息")
@Data
@TableName("t_sys_menu")
public class SysMenu extends Model implements Comparable<SysMenu>{

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    @TableId
    private Integer menuId;

    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Integer pid;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单顺序
     */
    @ApiModelProperty("菜单排序")
    private Integer sort;

    /**
     * 菜单图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 页面路径
     */
    @ApiModelProperty("跳转路径")
    private String url;

    /**
     * 是否启用 0 未启用: 1启动
     */
    @ApiModelProperty("状态(0-未启动,1-启动)")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> subMenu = new ArrayList<>();



    @Override
    public int compareTo(SysMenu menu) {
        return this.sort - menu.sort;
    }
}
