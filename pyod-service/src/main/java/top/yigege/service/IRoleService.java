package top.yigege.service;

import top.yigege.model.Permission;
import top.yigege.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;
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

    /**
     * 绑定角色菜单
     * @param roleId
     * @param menuIds
     */
    void bindRoleMenu(Integer roleId, List<Integer> menuIds);

    /**
     * 分页查询角色信息
     * @param page
     * @param pageSize
     * @param paramMap
     * @return
     */
    PageBean queryRoleList(int page, Integer pageSize, Map<String, Object> paramMap);

    /**
     * 删除角色 包含菜单和权限记录
     * @param roleIds
     */
    void deleteRoleContainsRecord(List<Integer> roleIds);

    /**
     * 添加或更新角色
     * @param role
     * @param menuIds
     * @param permissionIds
     * @return
     */
    Role addOrUpdateRole(Role role, List<Integer> menuIds, List<Integer> permissionIds);

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
    List<Permission> queryAllPermissionByRoleId(Integer roleId);
}
