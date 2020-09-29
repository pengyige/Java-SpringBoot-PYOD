package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.model.User;
import top.yigege.service.IUserService;
import top.yigege.dao.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> queryUserByNickname(List<String> nickname) {
        return userMapper.queryUserByNickname(nickname);
    }

    @Override
    public User queryUserRoles(String no) {
        return userMapper.queryUserRoles(no);
    }


    @Transactional
    @Override
    public void bindUserRoles(Integer userId, List<Integer> roleIds) {
        //1.先解绑所有角色
        userMapper.deleteUserRoles(userId);

        //2. 绑定现有角色
        userMapper.addUserRoleRecord(userId, roleIds);
    }

    @Override
    public IPage<User> queryUserList(int page, int pageSize) {
        Page<User> userPage = new Page<>(page, pageSize == 0 ? 10 : pageSize);
        return null;
    }
}
