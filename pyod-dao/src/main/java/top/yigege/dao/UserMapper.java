package top.yigege.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yigege.model.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserByNickname(List<String> nickname);

    User queryUserRoles(String no);

    void deleteUserRoles(Integer userId);

    void addUserRoleRecord(Integer userId,List<Integer> roleIds);
}
