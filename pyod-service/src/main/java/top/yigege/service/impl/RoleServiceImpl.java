package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.Role;
import top.yigege.dao.RoleMapper;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    IGenerateIDService iGenerateIDService;

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

    @Override
    public void bindRoleMenu(Integer roleId, List<Integer> menuIds) {
        //1.先解绑所有菜单
        roleMapper.deleteRoleMenuRecord(roleId);

        //2. 绑定现有菜单
        roleMapper.addRoleMenuRecord(roleId, menuIds);
    }

    @Override
    public PageBean queryRoleList(int page, Integer pageSize, Map<String, Object> paramMap) {
        Page pageInfo = new Page(page, pageSize == 0 ? 10 : pageSize);
        List<Role> roleList = roleMapper.queryRoleList(paramMap, pageInfo);
        return PageUtil.getPageBean(pageInfo, roleList);
    }

    @Transactional
    @Override
    public void deleteRoleContainsRecord(List<Integer> roleIds) {
        for (Integer roleId : roleIds) {
            //1.解绑所有角色
            roleMapper.deleteRolePermissionRecord(roleId);

            //2.解绑所有菜单
            roleMapper.deleteRoleMenuRecord(roleId);

            //3.删除该角色
            removeById(roleId);
        }
    }

    @Transactional
    @Override
    public Role addOrUpdateRole(Role role, List<Integer> menuIds, List<Integer> permissionIds) {
        if (null == role.getRoleId()) {
            role.setRoleNo(iGenerateIDService.getNo(BusinessFlagEnum.ROLE.getMsg()));

        }
        saveOrUpdate(role);

        bindRoleMenu(role.getRoleId(),menuIds);

        bindRolePermission(role.getRoleId(),permissionIds);

        return queryRoleInfo(role.getRoleId());
    }
}
