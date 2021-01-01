package top.yigege.dto.modules.sysMenu;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddSysMenuDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2020年12月31日 16:00
 */
@Data
public class AddSysMenuDTO {

    /**
     * 父ID
     */
    @NotNull(message = "菜单父id不能为空")
    private Integer pid;

    /**
     * 菜单名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 菜单顺序
     */
    @NotNull(message = "菜单顺序不能为空")
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
