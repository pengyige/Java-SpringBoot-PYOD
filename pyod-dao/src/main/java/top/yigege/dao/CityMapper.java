package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.city.QueryCityPageListDTO;
import top.yigege.model.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 城市 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CityMapper extends BaseMapper<City> {

    List<City> queryCityPageList(QueryCityPageListDTO queryCityPageListDTO, Page pageInfo);
}
