package top.yigege.dto.modules.sysUser;

import lombok.Data;
import top.yigege.constant.UserStatus;
import top.yigege.model.Shop;

import java.util.Date;

/**
 * @ClassName: MerchantUserLoginResDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月31日 13:05
 */
@Data
public class MerchantUserLoginResDTO {

    private String token;

    private String nickname;

    private String no;

    private String password;

    private Integer sex;

    private String tel;

    private Date lastLoginTime;

    private Shop shop;

}
