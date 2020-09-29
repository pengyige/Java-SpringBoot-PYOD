package top.yigege.dao;

import top.yigege.model.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role queryRoleInfo(Integer roleId);

    void deleteRolePermissionRecord(Integer roleId);

    void addRolePermissionRecord(Integer roleId, List<Integer> permissionIds);
}
