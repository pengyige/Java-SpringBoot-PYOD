package top.yigege.service;

import top.yigege.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.LayuiTreeBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2020-10-14
 */
public interface IMenuService extends IService<Menu> {

    List<LayuiTreeBean> queryTreeMenu();


}
