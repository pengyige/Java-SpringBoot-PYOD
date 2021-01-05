package top.yigege.dto.modules.sysUser;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.yigege.constant.SexType;
import top.yigege.constant.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName: AddUserDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月03日 14:49
 */
@Data
public class AddUserDTO {

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    @Length(min = 2,max = 6,message = "用户昵称长度最小2位，最大6位")
    private String nickname;

    /**
     * 性别;0:男 1:女
     */
    private Integer sex = SexType.NONE.getCode();

    /**
     * 联系方式
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String tel;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 12)
    private String password;

    /**
     * 用户状态
     */
    private Integer status = UserStatus.NORMAL.getCode();

    @NotBlank(message = "角色id不能为空")
    String roleIds;

    /**
     * 备注
     */
    private String remark;

}
