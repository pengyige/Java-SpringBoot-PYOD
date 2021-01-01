package top.yigege.service.impl;


import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.BusinessFlagEnum;

import top.yigege.model.SysMenu;
import top.yigege.model.SysUser;

import top.yigege.service.IGenerateIDService;
import top.yigege.service.ISysUserService;
import top.yigege.dao.*;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static top.yigege.constant.PyodConstant.Common.PARENT_MENU_FLAG;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    SysUserMapper userMapper;

    @Autowired
    IGenerateIDService iGenerateIDService;

    @Override
    public List<SysUser> queryUserByNickname(List<String> nickname) {
        return userMapper.queryUserByNickname(nickname);
    }

    @Override
    public SysUser queryUserRoles(String no) {
        return userMapper.queryUserRoles(no);
    }

    @Override
    public SysUser queryUserRolesById(Integer id) {
        return userMapper.queryUserRolesById(id);
    }

    @Transactional
    @Override
    public void bindUserRoles(Integer userId, List<Integer> roleIds) {
        //1.先解绑所有角色
        if (null != userId) {
             userMapper.deleteUserRoles(userId);
        }
        //2. 绑定现有角色
        if (!roleIds.isEmpty()) {
            userMapper.addUserRoleRecord(userId, roleIds);
        }
    }

    @Override
    public PageBean queryUserList(int page, int pageSize, Map paramMap) {

        Page pageInfo = new Page(page, pageSize == 0 ? 10 : pageSize);
        List<SysUser> userList = userMapper.queryAllUser(paramMap, pageInfo);
        return PageUtil.getPageBean(pageInfo, userList);
    }

    @Override
    public List<SysMenu> queryMenusByRoleNo(String roleNo) {

        List<SysMenu> totalMenuList = userMapper.queryMenusByRoleNo(roleNo);

        List<SysMenu> menuTree = new ArrayList<>();
        for (SysMenu menu : totalMenuList) {
            if (PARENT_MENU_FLAG == menu.getPid()) {
                menuTree.add(findChildren(menu, totalMenuList));
            }
        }

        if (!menuTree.isEmpty()) {
            //对该菜单按照sort排序
            Collections.sort(menuTree);
        }


        return menuTree;
    }


    @Transactional
    @Override
    public SysUser addUser(SysUser user, List<Integer> roleIds) {
        //为新增时、添加NO
        user.setNo(iGenerateIDService.getNo(BusinessFlagEnum.USER.getMsg()));
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        save(user);

        if (null != user.getUserId()) {
            bindUserRoles(user.getUserId(), roleIds);
        }

        return queryUserRoles(user.getNo());
    }

    @Transactional
    @Override
    public void deleteUserById(Integer id) {
        removeById(id);

        //清空该用户的角色记录
        userMapper.deleteUserRoles(id);
    }

    /**
     * 递归查找子节点
     * @param menu
     * @param menuList
     * @return
     */
    private SysMenu findChildren(SysMenu menu, List<SysMenu> menuList) {

        for (SysMenu menuItem : menuList) {
            //是否存在menu的子节点
            if (menu.getMenuId().equals(menuItem.getPid())) {
                menu.getSubMenu().add(findChildren(menuItem, menuList));
            }
        }

        if (!menu.getSubMenu().isEmpty()) {
            //对该菜单的所有子节点按照sort排序
            Collections.sort(menu.getSubMenu());
        }

        return menu;
    }
}
