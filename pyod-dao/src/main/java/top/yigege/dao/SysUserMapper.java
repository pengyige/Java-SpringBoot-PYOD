package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.apache.ibatis.annotations.Mapper;

import top.yigege.dto.modules.sysUser.QueryUserPageListDTO;
import top.yigege.model.SysMenu;
import top.yigege.model.SysUser;

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
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> queryUserByNickname(List<String> nickname);

    List<SysUser> queryAllUser(QueryUserPageListDTO queryUserPageListDTO, IPage iPage);

    SysUser queryUserRoles(String no);

    SysUser queryUserRolesById(Integer id);

    void deleteUserRoles(Integer userId);

    void addUserRoleRecord(Integer userId,List<Integer> roleIds);

    List<SysMenu> queryMenusByRoleNo(String no);


}
