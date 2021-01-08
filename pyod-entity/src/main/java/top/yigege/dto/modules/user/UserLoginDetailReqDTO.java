package top.yigege.dto.modules.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserLoginDetailReqDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 19:44
 */
@Data
public class UserLoginDetailReqDTO {

    @NotBlank(message = "openid不能为空")
    private String openid;

    @NotBlank(message = "向量不能为空")
    private String iv;

    @NotBlank(message = "加密数据不能为空")
    private String encryptedData;
}
