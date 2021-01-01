package top.yigege.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2020-12-31
 */
@Data
@TableName("t_sys_role_perm_relation")
public class SysRolePermRelation extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限关联id
     */
    @TableId(value = "role_perm_relation_id", type = IdType.AUTO)
    private Long rolePermRelationId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
