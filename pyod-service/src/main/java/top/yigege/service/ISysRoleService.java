package top.yigege.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.sysRole.AddRoleDTO;
import top.yigege.dto.modules.sysRole.ModifyRoleDTO;
import top.yigege.dto.modules.sysRole.QueryRolePageListDTO;
import top.yigege.model.SysPermission;
import top.yigege.model.SysRole;
import top.yigege.vo.LayuiTreeBean;
import top.yigege.vo.PageBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询角色信息 包含权限信息
     * @param roleId
     */
    SysRole queryRoleInfo(Integer roleId);


    /**
     * 绑定角色权限
     * @param roleId
     * @param permissionIds
     */
    void bindRolePermission(Integer roleId, List<Integer> permissionIds);

    /**
     * 绑定角色菜单
     * @param roleId
     * @param menuIds
     */
    void bindRoleMenu(Integer roleId, List<Integer> menuIds);


    /**
     * 分页查询角色信息
     * @param queryRolePageListDTO
     * @return
     */
    PageBean queryRoleList(QueryRolePageListDTO queryRolePageListDTO);

    /**
     * 删除角色 包含菜单和权限记录
     * @param roleIds
     */
    void deleteRoleContainsRecord(List<Integer> roleIds);


    /**
     * 添加角色
     * @param addRoleDTO
     * @return
     */
    SysRole addRole(AddRoleDTO addRoleDTO);

    /**
     * 修改角色
     * @param modifyRoleDTO
     * @return
     */
    SysRole modifyRole(ModifyRoleDTO modifyRoleDTO);


    /**
     * 查询选中的菜单
     * @param roleId
     * @return
     */
    List<LayuiTreeBean> queryCheckedMenusByRoleId(Integer roleId);

    /**
     * 查询所有菜单，同时添加选中
     * @param roleId
     * @return
     */
    List<LayuiTreeBean> queryMenusByRoleId(Integer roleId);

    /**
     * 通过角色ID获取权限
     * @param roleId
     * @return
     */
    List<SysPermission> queryAllPermissionByRoleId(Integer roleId);
}
