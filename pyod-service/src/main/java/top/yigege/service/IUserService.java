package top.yigege.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.Menu;
import top.yigege.model.User;
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
public interface IUserService extends IService<User> {

    /**
     * 通过用户名查询用户
     * @param nickname
     * @return
     */
    List<User> queryUserByNickname(List<String> nickname);

    /**
     * 通过用户编号查询用户
     * @param no
     * @return
     */
    User queryUserRoles(String no);

    /**
     * 通过ID查询用户角色
     * @param id
     * @return
     */
    User queryUserRolesById(Integer id);

    /**
     * 查询用户列表
     * @param page
     * @param pageSize
     * @param paramMap
     * @return
     */
    PageBean queryUserList(int page, int pageSize, Map paramMap);


    /**
     * 通过角色编号查询菜单
     * @param roleNo
     * @return
     */
    List<Menu> queryMenusByRoleNo(String roleNo);


    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    User addUser(User user, List<Integer> roleIds);

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
