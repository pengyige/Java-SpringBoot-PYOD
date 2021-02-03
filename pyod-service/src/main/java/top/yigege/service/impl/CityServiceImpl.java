package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.constant.CityTypeEnum;
import top.yigege.constant.YesOrNo;
import top.yigege.dto.modules.city.QueryCityPageListDTO;
import top.yigege.model.City;
import top.yigege.dao.CityMapper;
import top.yigege.model.Level;
import top.yigege.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 城市 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Resource
    CityMapper cityMapper;

    @Override
    public List<City> queryHotCity() {
        LambdaQueryWrapper<City> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(City::getType, CityTypeEnum.CITY.getCode());
        lambdaQueryWrapper.eq(City::getHotFlag, YesOrNo.YES.getCode());
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<City> queryAllCity() {
        LambdaQueryWrapper<City> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(City::getType, CityTypeEnum.CITY.getCode());
        return list(lambdaQueryWrapper);
    }

    @Override
    public PageBean queryCityPageList(QueryCityPageListDTO queryCityPageListDTO) {
        Page pageInfo = new Page(queryCityPageListDTO.getPage(),
                queryCityPageListDTO.getPageSize() == 0 ? 10 : queryCityPageListDTO.getPageSize());
        List<City> cityList = cityMapper.queryCityPageList(queryCityPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, cityList);

    }
}
