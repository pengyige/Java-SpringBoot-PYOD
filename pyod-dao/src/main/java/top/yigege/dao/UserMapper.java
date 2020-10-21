package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.apache.ibatis.annotations.Mapper;
import top.yigege.model.Menu;
import top.yigege.model.User;
import top.yigege.vo.LayuiTreeBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserByNickname(List<String> nickname);

    List<User> queryAllUser(Map paramMap, IPage iPage);

    User queryUserRoles(String no);

    void deleteUserRoles(Integer userId);

    void addUserRoleRecord(Integer userId,List<Integer> roleIds);

    List<Menu> queryMenusByUserNo(String no);

}
