package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yigege.model.SysMenu;

import top.yigege.vo.LayuiTreeBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2020-10-14
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询所有菜单
     * @return
     */
    List<LayuiTreeBean> queryTreeMenu();
}
