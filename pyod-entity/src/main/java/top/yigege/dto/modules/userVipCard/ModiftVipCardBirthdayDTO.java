package top.yigege.dto.modules.userVipCard;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: ModiftVipCardBirthdayDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月22日 20:46
 */
@Data
public class ModiftVipCardBirthdayDTO {

    @ApiModelProperty(value = "会员卡ID",required = true)
    @NotNull(message = "会员卡ID不能为空")
    Long vipCardId;

    @ApiModelProperty(value = "生日",required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "生日不能为空")
    Date birthday;

    Long userId;

}
