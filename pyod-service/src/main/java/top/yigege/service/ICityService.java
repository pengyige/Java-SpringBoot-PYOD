package top.yigege.service;

import top.yigege.dto.modules.city.QueryCityPageListDTO;
import top.yigege.model.City;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICityService extends IService<City> {

    /**
     * 查询热门城市
     * @return
     */
    List<City> queryHotCity();

    /**
     * 查询所有城市
     * @return
     */
    List<City> queryAllCity();

    /**
     * 城市分页列表
     * @param queryCityPageListDTO
     * @return
     */
    PageBean queryCityPageList(QueryCityPageListDTO queryCityPageListDTO);
}
