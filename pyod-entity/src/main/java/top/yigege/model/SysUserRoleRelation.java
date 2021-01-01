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
@TableName("t_sys_user_role_relation")
public class SysUserRoleRelation extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色
     */
    @TableId(value = "user_role_relation_id", type = IdType.AUTO)
    private Long userRoleRelationId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
