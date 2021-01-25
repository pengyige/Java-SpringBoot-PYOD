package top.yigege.dto.modules.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: UserLoginDetailReqDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 19:44
 */
@Data
public class UserLoginDetailReqDTO {

    @ApiModelProperty(value = "merchantId", required = true)
    @NotNull(message = "商家ID不能为空")
    private Long merchantId;

    @ApiModelProperty(value = "openid", required = true)
    @NotBlank(message = "openid不能为空")
    private String openid;

    @ApiModelProperty(value = "向量", required = true)
    @NotBlank(message = "向量不能为空")
    private String iv;

    @ApiModelProperty(value = "加密数据", required = true)
    @NotBlank(message = "加密数据不能为空")
    private String encryptedData;
}
