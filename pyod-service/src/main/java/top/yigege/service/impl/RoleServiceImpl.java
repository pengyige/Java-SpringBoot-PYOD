package top.yigege.service.impl;

import org.springframework.transaction.annotation.Transactional;
import top.yigege.model.Role;
import top.yigege.dao.RoleMapper;
import top.yigege.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public Role queryRoleInfo(Integer roleId) {
       return roleMapper.queryRoleInfo(roleId);
    }


    @Transactional
    @Override
    public void bindRolePermission(Integer roleId, List<Integer> permissionIds) {
        //1.先解绑所有角色
        roleMapper.deleteRolePermissionRecord(roleId);

        //2. 绑定现有角色
        roleMapper.addRolePermissionRecord(roleId, permissionIds);
    }
}
