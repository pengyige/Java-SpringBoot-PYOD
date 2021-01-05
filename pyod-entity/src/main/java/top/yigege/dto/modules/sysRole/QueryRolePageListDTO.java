package top.yigege.dto.modules.sysRole;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: QueryRolePageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 16:20
 */
@Data
public class QueryRolePageListDTO {

    int page = 1;

    Integer pageSize = 10;

    Integer roleId;

    String name;

    String roleNo;
}
