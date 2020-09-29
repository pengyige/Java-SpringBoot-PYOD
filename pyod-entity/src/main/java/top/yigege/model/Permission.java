package top.yigege.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId("permission_id")
    private Integer permissionId;

    /**
     * 权限名
     */
    @NotBlank(message = "权限名称不能为空")
    private String name;

    /**
     * 权限备注
     */
    private String remark;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.permissionId;
    }

    @Override
    public String toString() {
        return "Permission{" +
        "permissionId=" + permissionId +
        ", name=" + name +
        ", remark=" + remark +
        "}";
    }
}
