package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.yigege.dao.CityMapper;
import top.yigege.model.City;
import top.yigege.service.ICityService;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-08
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Override
    public City queryCityDetail(String province, String city, String district) {
        LambdaQueryWrapper<City> cityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(province)) {
            cityLambdaQueryWrapper.eq(City::getProvinceZh,province.replace("省","")
                    .replace("市",""));
        }

        if (StringUtils.isNotBlank(city) && !city.equals("[]")) {
             cityLambdaQueryWrapper.eq(City::getLeaderZh,city.replace("市",""));
        }

        if (StringUtils.isNotBlank(district)) {
            cityLambdaQueryWrapper.likeRight(City::getCityZh,district.replace("区",""));
        }
        return getOne(cityLambdaQueryWrapper);
    }
}
