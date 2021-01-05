package top.yigege.dto.modules.sysUser;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ModifyUserDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 15:04
 */
@Data
public class ModifyUserDTO {

    @NotNull(message = "用户ID不能为空")
    Integer userId;

    String nickname;

    Integer sex;

    String tel;

    String password;

    String remark;

    String roleIds;
}
