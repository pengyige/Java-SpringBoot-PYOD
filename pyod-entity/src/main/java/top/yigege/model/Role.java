package top.yigege.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
@Data
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色编号
     */
    @TableField("role_no")
    private String roleNo;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注说明
     */
    private String remark;

    @TableField(exist = false)
    private List<Permission> permissionList;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

}
