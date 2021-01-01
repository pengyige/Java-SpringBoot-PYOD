package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import top.yigege.model.SysPermission;
import top.yigege.model.SysRole;
import top.yigege.vo.LayuiTreeBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    SysRole queryRoleInfo(Integer roleId);

    void deleteRolePermissionRecord(Integer roleId);

    void deleteRoleMenuRecord(Integer roleId);

    void addRolePermissionRecord(Integer roleId, List<Integer> permissionIds);

    void addRoleMenuRecord(Integer roleId, List<Integer> menuIds);

    List<SysRole> queryRoleList(Map<String, Object> paramMap, Page pageInfo);

    List<LayuiTreeBean> queryTreeMenusByRoleId(Integer roleId);

    List<SysPermission> queryAllPermissionByRoleId(Integer roleId);
}
