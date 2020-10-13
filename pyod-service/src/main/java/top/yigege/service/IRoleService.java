package top.yigege.service;

import top.yigege.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询角色信息 包含权限信息
     * @param roleId
     */
    Role queryRoleInfo(Integer roleId);

    /**
     * 绑定角色权限
     * @param roleId
     * @param permissionIds
     */
    void bindRolePermission(Integer roleId, List<Integer> permissionIds);
}
