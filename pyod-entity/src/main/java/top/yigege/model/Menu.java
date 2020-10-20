package top.yigege.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

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
    private List<Menu> subMenus = new ArrayList<>();


    public List<Menu> getSubMenu() {
        return subMenus;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenus = subMenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", sort=" + sort +
        ", icon=" + icon +
        ", url=" + url +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }

    @Override
    public int compareTo(Menu menu) {
        return this.sort - menu.sort;
    }
}
