package top.yigege.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户信息")
public class User extends Model<User> {

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
     * 性别;0:男 1:女
     */
    @ApiModelProperty("用户性别 0:男 1:女")
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
    private String remark;

    @TableField(exist = false)
    private List<Role> roleList = new ArrayList<>();

    public List<Role> getRoleList() {
        return roleList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", no='" + no + '\'' +
                ", sex=" + sex +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
