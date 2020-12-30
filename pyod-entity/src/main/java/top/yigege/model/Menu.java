package top.yigege.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2020-10-14
 */
@Data
public class Menu extends Model implements Comparable<Menu>{

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单顺序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 页面路径
     */
    private String url;

    /**
     * 是否启用 0 未启用: 1启动
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> subMenu = new ArrayList<>();



    @Override
    public int compareTo(Menu menu) {
        return this.sort - menu.sort;
    }
}
