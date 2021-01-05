package top.yigege.dto.modules.sysUser;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import top.yigege.constant.PyodConstant;

/**
 * @ClassName: QueryUserPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 15:27
 */
@Data
public class QueryUserPageListDTO {

    int page = 1;

    int pageSize = 10;

    Integer userId;

    String nickname;

    String no;
    Integer sex;
    String tel;

    Integer status;
}
