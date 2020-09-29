package top.yigege.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.service.IService;
import top.yigege.model.User;

import java.util.List;

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
     * 绑定用户角色
     * @param userId
     * @param roleIds
     */
    void bindUserRoles(Integer userId,List<Integer> roleIds);

    /**
     * 查询用户列表
     * @param page
     * @param pageSize
     * @return
     */
    IPage<User> queryUserList(int page, int pageSize);
}
