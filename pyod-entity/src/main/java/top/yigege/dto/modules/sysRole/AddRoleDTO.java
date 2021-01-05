package top.yigege.dto.modules.sysRole;

import lombok.Data;
import top.yigege.model.SysRole;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: AddRoleDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 16:32
 */
@Data
public class AddRoleDTO {

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    String name;

    /**
     * 备注
     */
    String remark;

    /**
     * 菜单id，多个逗号分隔
     */
    String menuIds;

    /**
     * 权限id，多个逗号分隔
     */
    String permissionIds;
}
