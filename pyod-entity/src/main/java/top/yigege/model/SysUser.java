package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.yigege.constant.SexType;
import top.yigege.constant.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
@Data
@TableName("t_sys_user")
@ApiModel("系统用户")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    @Length(min = 2,max = 6,message = "用户昵称长度最小2位，最大6位")
    private String nickname;

    @ApiModelProperty("用户编号")
    private String no;

    /**
     * 性别;1:男 2:女
     */
    @ApiModelProperty("用户性别 1:男 2:女")
    private Integer sex = SexType.NONE.getCode();

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String tel;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 12)
    private String password;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status = UserStatus.NORMAL.getCode();

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<SysRole> roleList = new ArrayList<>();




    @Override
    protected Serializable pkVal() {
        return this.userId;
    }


}
