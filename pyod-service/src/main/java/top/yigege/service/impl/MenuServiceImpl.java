package top.yigege.service.impl;

import top.yigege.model.Menu;
import top.yigege.dao.*;
import top.yigege.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.vo.LayuiTreeBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static top.yigege.constant.PyodConstant.Common.PARENT_MENU_FLAG;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2020-10-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<LayuiTreeBean> queryTreeMenu() {
        List<LayuiTreeBean> totalMenuList = menuMapper.queryTreeMenu();

        List<LayuiTreeBean> menuTree = new ArrayList<>();
        for (LayuiTreeBean menu : totalMenuList) {
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



    /**
     * 递归查找子节点
     * @param menu
     * @param menuList
     * @return
     */
    private LayuiTreeBean findChildren(LayuiTreeBean menu, List<LayuiTreeBean> menuList) {

        for (LayuiTreeBean menuItem : menuList) {
            //是否存在menu的子节点
            if (menu.getId() == menuItem.getPid()) {
                menu.getChildren().add(findChildren(menuItem, menuList));
            }
        }

        if (!menu.getChildren().isEmpty()) {
            //对该菜单的所有子节点按照sort排序
            Collections.sort(menu.getChildren());
        }

        return menu;
    }
}
