package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.dao.MenuMapper;
import top.yigege.model.Permission;
import top.yigege.model.Role;
import top.yigege.dao.RoleMapper;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.LayuiTreeBean;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static top.yigege.constant.PyodConstant.Common.PARENT_MENU_FLAG;

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

    @Resource
    MenuMapper menuMapper;

    @Override
    public Role queryRoleInfo(Integer roleId) {
       return roleMapper.queryRoleInfo(roleId);
    }

    @Transactional
    @Override
    public void bindRolePermission(Integer roleId, List<Integer> permissionIds) {
        //1.先解绑所有角色
        if (null != roleId) {
         roleMapper.deleteRolePermissionRecord(roleId);
        }
        //2. 绑定现有角色
        if (!permissionIds.isEmpty()) {
            roleMapper.addRolePermissionRecord(roleId, permissionIds);
        }
    }

    @Transactional
    @Override
    public void bindRoleMenu(Integer roleId, List<Integer> menuIds) {
        //1.先解绑所有菜单
        if (null != roleId) {
          roleMapper.deleteRoleMenuRecord(roleId);
        }
        //2. 绑定现有菜单
        if (!menuIds.isEmpty()) {
            roleMapper.addRoleMenuRecord(roleId, menuIds);
        }
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

        if (!menuIds.isEmpty()) {
            bindRoleMenu(role.getRoleId(),menuIds);
        }

        if (!permissionIds.isEmpty()) {
            bindRolePermission(role.getRoleId(),permissionIds);
        }

        return queryRoleInfo(role.getRoleId());
    }

    @Override
    public List<LayuiTreeBean> queryCheckedMenusByRoleId(Integer roleId) {
        List<LayuiTreeBean> totalMenuList = roleMapper.queryTreeMenusByRoleId(roleId);

        List<LayuiTreeBean> menuTree = new ArrayList<>();
        for (LayuiTreeBean menu : totalMenuList) {
            if (PARENT_MENU_FLAG == menu.getPid()) {
                menuTree.add(findChildren(menu, totalMenuList));
            }
        }

        if (!menuTree.isEmpty()) {
            //对该菜单按照sort排序
            Collections.sort(menuTree);
        }


        return menuTree;
    }

    @Override
    public List<LayuiTreeBean> queryMenusByRoleId(Integer roleId) {
        List<LayuiTreeBean> totalMenuList = menuMapper.queryTreeMenu();

        List<LayuiTreeBean> totalCheckedMenuList = roleMapper.queryTreeMenusByRoleId(roleId);

        //设置当前已选中
        for (LayuiTreeBean currentCheckMenu : totalCheckedMenuList) {
            for (LayuiTreeBean item : totalMenuList) {
                if (currentCheckMenu.getId() == item.getId()) {
                    item.setChecked(true);
                    break;
                }
            }
        }

        //建树
        List<LayuiTreeBean> menuTree = new ArrayList<>();
        for (LayuiTreeBean menu : totalMenuList) {
            if (PARENT_MENU_FLAG == menu.getPid()) {
                menuTree.add(findChildren(menu, totalMenuList));
            }
        }

        if (!menuTree.isEmpty()) {
            //对该菜单按照sort排序
            Collections.sort(menuTree);
        }

        return menuTree;
    }

    @Override
    public List<Permission> queryAllPermissionByRoleId(Integer roleId) {
        return roleMapper.queryAllPermissionByRoleId(roleId);
    }


    /**
     * 递归查找子节点
     * @param menu
     * @param menuList
     * @return
     */
    private LayuiTreeBean findChildren(LayuiTreeBean menu, List<LayuiTreeBean> menuList) {

        for (LayuiTreeBean menuItem : menuList) {
            //是否存在menu的子节点
            if (menu.getId() == menuItem.getPid()) {
                menu.getChildren().add(findChildren(menuItem, menuList));
            }
        }

        if (!menu.getChildren().isEmpty()) {
            //对该菜单的所有子节点按照sort排序
            Collections.sort(menu.getChildren());
        }

        return menu;
    }
}
