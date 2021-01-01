package top.yigege.service;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.SysMenu;
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
public interface ISysMenuService extends IService<SysMenu> {

    List<LayuiTreeBean> queryTreeMenu();

}
