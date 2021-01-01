package top.yigege.dto.modules.sysMenu;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddSysMenuDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2020年12月31日 16:00
 */
@Data
public class ModifySysMenuDTO {

    @NotNull(message = "菜单id不能为空")
    private Integer menuId;

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

}
