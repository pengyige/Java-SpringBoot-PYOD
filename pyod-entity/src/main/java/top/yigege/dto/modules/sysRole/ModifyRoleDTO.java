package top.yigege.dto.modules.sysRole;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddRoleDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 16:32
 */
@Data
public class ModifyRoleDTO {

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
   Integer roleId;

    /**
     * 名称
     */
    String name;

    /**
     * 备注
     */
    String remark;

    /**
     * 菜单id
     */
    String menuIds;

    /**
     * 权限id
     */
    String permissionIds;
}
