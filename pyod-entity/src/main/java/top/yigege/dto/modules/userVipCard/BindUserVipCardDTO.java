package top.yigege.dto.modules.userVipCard;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.yigege.constant.YesOrNo;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: BindUserVipCardDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月13日 21:30
 */
@Data
public class BindUserVipCardDTO {

    @ApiModelProperty(value = "卡号", required = true)
    @NotBlank(message = "卡号不能为空")
    String cardNo;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    String cardPsw;

    /**
     * 主卡标识
     */
    @ApiModelProperty(value = "主卡标识(0-非主卡,1-主卡.默认为主卡)")
    int primaryFlag = YesOrNo.YES.getCode();
}
