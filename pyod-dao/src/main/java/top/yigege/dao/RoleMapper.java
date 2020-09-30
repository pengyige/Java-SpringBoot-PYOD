package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yigege.model.Role;

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
