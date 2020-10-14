package top.yigege.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.model.Menu;
import top.yigege.model.User;
import top.yigege.service.IUserService;
import top.yigege.dao.*;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public PageBean queryUserList(int page, int pageSize, Map paramMap) {

        Page pageInfo = new Page(page, pageSize == 0 ? 10 : pageSize);
        List<User> userList = userMapper.queryAllUser(paramMap, pageInfo);
        return PageUtil.getPageBean(pageInfo, userList);
    }

    @Override
    public List<Menu> queryMenusByUserNo(String userNo) {

        List<Menu> menuList = userMapper.queryMenusByUserNo(userNo);


        List<Menu> menuTree = new ArrayList<>();
        for (Menu menu : menuList) {
            if (PARENT_MENU_FLAG == menu.getPid()) {
                menuTree.add(findChildren(menu, menuList));
            }
        }

        return menuList;
    }

    /**
     * 递归查找子节点
     * @param menu
     * @param menuList
     * @return
     */
    private Menu findChildren(Menu menu, List<Menu> menuList) {

        for (Menu menuItem : menuList) {
            //是否存在menu的子节点
            if (menu.getId() == menuItem.getPid()) {
                if (menu.getSubMenu() == null) {
                    //第一次添加子节点时初始化一下list
                    menu.setSubMenu(new ArrayList<>());
                }
                menu.getSubMenu().add(findChildren(menuItem, menuList));
            }
        }

        return menu;
    }
}
