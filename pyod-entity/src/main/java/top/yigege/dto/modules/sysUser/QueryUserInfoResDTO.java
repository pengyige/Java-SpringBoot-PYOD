package top.yigege.dto.modules.sysUser;

import lombok.Data;
import top.yigege.model.SysRole;
import top.yigege.model.SysUser;

/**
 * @ClassName: QueryUserInfoResDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 15:16
 */
@Data
public class QueryUserInfoResDTO {

    SysUser user;

    SysRole checkedRole;
}
