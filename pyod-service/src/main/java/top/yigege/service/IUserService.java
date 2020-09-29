package top.yigege.service;

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

    void bindUserRoles(List<Integer> roleIds);

}
