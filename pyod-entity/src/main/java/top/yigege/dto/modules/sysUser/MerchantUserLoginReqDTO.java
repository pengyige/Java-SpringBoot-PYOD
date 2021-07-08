package top.yigege.dto.modules.sysUser;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: MerchantUserLoginReqDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月31日 13:02
 */
@Data
public class MerchantUserLoginReqDTO {

    @NotBlank(message = "编号/手机号不能为空")
    private String no;

    @NotBlank(message = "密码不能为空")
    private String password;
}
