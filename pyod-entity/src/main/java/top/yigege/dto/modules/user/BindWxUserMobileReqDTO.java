package top.yigege.dto.modules.user;

import io.swagger.annotations.ApiModel;
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
@ApiModel("绑定微信手机号")
public class BindWxUserMobileReqDTO {

    @ApiModelProperty(value = "merchantId", required = true)
    @NotNull(message = "商家ID不能为空")
    private Long merchantId;

    @ApiModelProperty(value = "openid",dataType = "string",required = true,example = "ojWgU5dNVC0IhdKpczE3YU7dMa2I")
    @NotBlank(message = "openid不能为空")
    private String openid;

    @ApiModelProperty(value = "向量",dataType = "string",required = true,example = "8bLSTI4y97PBAzRyNqIQEg")
    @NotBlank(message = "向量不能为空")
    private String iv;

    @ApiModelProperty(value = "加密数据",dataType = "string",required = true,example = "4tUGrigRfiz/bW/jG+iwwQvrUj+d...")
    @NotBlank(message = "加密数据不能为空")
    private String encryptedData;
}
