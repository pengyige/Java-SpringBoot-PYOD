package top.yigege.dto.modules.user;

import lombok.Data;

/**
 * @ClassName: UserQrCodeDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月11日 12:14
 */
@Data
public class UserQrCodeDTO {

    private String token;

    private long expireTime;
}
