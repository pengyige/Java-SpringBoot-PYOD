package top.yigege.dto.modules.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: ModifyUserInfoDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 15:57
 */
@Data
public class ModifyUserInfoDTO {

    @ApiModelProperty(value = "生日",required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date birthday;

    Long userId;
}
