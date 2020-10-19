package top.yigege.dao;

import top.yigege.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yigege.vo.LayuiTableResultBean;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<LayuiTreeBean> queryTreeMenu();
}
