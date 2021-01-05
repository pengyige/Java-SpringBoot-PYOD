package top.yigege.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
;
import com.baomidou.mybatisplus.extension.service.IService;

import top.yigege.dto.modules.sysUser.AddUserDTO;
import top.yigege.dto.modules.sysUser.QueryUserPageListDTO;
import top.yigege.model.SysMenu;
import top.yigege.model.SysUser;

import top.yigege.vo.PageBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询用户
     * @param nickname
     * @return
     */
    List<SysUser> queryUserByNickname(List<String> nickname);

    /**
     * 通过用户编号查询用户
     * @param no
     * @return
     */
    SysUser queryUserRoles(String no);

    /**
     * 通过ID查询用户角色
     * @param userId
     * @return
     */
    SysUser queryUserRolesById(Integer userId);


    /**
     * 查询用户分页列表
     * @param queryUserPageListDTO
     * @return
     */
    PageBean queryUserList(QueryUserPageListDTO queryUserPageListDTO);


    /**
     * 通过角色编号查询菜单
     * @param roleNo
     * @return
     */
    List<SysMenu> queryMenusByRoleNo(String roleNo);


    /**
     * 添加用户
     * @param addUserDTO
     * @return
     */
    SysUser addUser(AddUserDTO addUserDTO);

    /**
     * 删除用户
     * @param id
     * @return
     */
    void deleteUserById(Integer id);



    /**
     * 绑定用户角色
     * @param userId
     * @param roleIds
     */
    void bindUserRoles(Integer userId,List<Integer> roleIds);

}
