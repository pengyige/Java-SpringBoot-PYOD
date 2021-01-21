package top.yigege.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.BusinessFlagEnum;

import top.yigege.constant.PyodConstant;
import top.yigege.dao.SysMenuMapper;

import top.yigege.dao.SysRoleMapper;
import top.yigege.dto.modules.sysRole.AddRoleDTO;
import top.yigege.dto.modules.sysRole.ModifyRoleDTO;
import top.yigege.dto.modules.sysRole.QueryRolePageListDTO;
import top.yigege.model.SysPermission;
import top.yigege.model.SysRole;

import top.yigege.service.IGenerateIDService;
import top.yigege.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.util.Utils;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    IGenerateIDService iGenerateIDService;

    @Resource
    SysRoleMapper roleMapper;

    @Resource
    SysMenuMapper menuMapper;

    @Override
    public SysRole queryRoleInfo(Integer roleId) {
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
    public PageBean queryRoleList(QueryRolePageListDTO queryRolePageListDTO) {
        Page pageInfo = new Page(queryRolePageListDTO.getPage()
                , queryRolePageListDTO.getPageSize() == 0 ? 10 :queryRolePageListDTO.getPageSize());
        List<SysRole> roleList = roleMapper.queryRoleList(queryRolePageListDTO, pageInfo);
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
    public SysRole addRole(AddRoleDTO addRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(addRoleDTO,sysRole);


        sysRole.setRoleNo(iGenerateIDService.getNo(BusinessFlagEnum.ROLE.getMsg()));

        save(sysRole);

        if (StringUtils.isNotBlank(addRoleDTO.getMenuIds())) {
            bindRoleMenu(sysRole.getRoleId(),
                    Utils.parseIntegersList(Utils.splitStringToList(addRoleDTO.getMenuIds())));
        }

        if (StringUtils.isNotBlank(addRoleDTO.getPermissionIds())) {
            bindRolePermission(sysRole.getRoleId(),
                    Utils.parseIntegersList(Utils.splitStringToList(addRoleDTO.getPermissionIds())));
        }

        return queryRoleInfo(sysRole.getRoleId());
    }

    @Transactional
    @Override
    public SysRole modifyRole(ModifyRoleDTO modifyRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(modifyRoleDTO,sysRole);

        updateById(sysRole);

        if (StringUtils.isNotBlank(modifyRoleDTO.getMenuIds())) {
            bindRoleMenu(sysRole.getRoleId(),
                    Utils.parseIntegersList(Utils.splitStringToList(modifyRoleDTO.getMenuIds())));
        }

        if (StringUtils.isNotBlank(modifyRoleDTO.getPermissionIds())) {
            bindRolePermission(sysRole.getRoleId(),
                    Utils.parseIntegersList(Utils.splitStringToList(modifyRoleDTO.getPermissionIds())));
        }

        return queryRoleInfo(sysRole.getRoleId());
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
        for (LayuiTreeBean item : totalMenuList) {
            boolean isChecked = false;
            for (LayuiTreeBean currentCheckMenu : totalCheckedMenuList) {
                if (currentCheckMenu.getId() == item.getId()) {
                    isChecked = true;
                    item.setChecked(true);
                    break;
                }
            }
            if (!isChecked) {
                item.setChecked(false);
                item.setTitle(item.getTitle()+"-未绑定");
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
    public List<SysPermission> queryAllPermissionByRoleId(Integer roleId) {
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
