package top.yigege.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yigege.config.WeatherConfig;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.weather.QueryRealtimeDTO;
import top.yigege.dto.modules.weather.QuerySevenDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.City;
import top.yigege.service.ICityService;
import top.yigege.service.IRedisService;
import top.yigege.service.IWeatherService;
import top.yigege.util.DateUtil;
import top.yigege.util.GaodeUtil;
import top.yigege.vo.gaode.AddressInfoResultBean;

/**
 * @ClassName: WeatherServiceImpl
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:14
 */
@Service
public class WeatherServiceImpl implements IWeatherService {

    @Autowired
    IRedisService iRedisService;

    @Autowired
    WeatherConfig weatherConfig;

    @Autowired
    ICityService iCityService;

    @Autowired
    GaodeUtil gaodeUtil;

    @Override
    public Object queryRealtimeWeather(QueryRealtimeDTO queryRealtimeDTO) {
        if (null == queryRealtimeDTO) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_PARAM);
        }

        String url = PyodConstant.WeatherApi.REALTIME_URL
                .replace("{appid}", weatherConfig.getAppId())
                .replace("{appsecret}", weatherConfig.getAppSecret());

        String res = "";
        String cityId = "";
        if (StringUtils.isNotBlank(queryRealtimeDTO.getCityId())) {
            cityId =  queryRealtimeDTO.getCityId();
        }else {
            if (queryRealtimeDTO.getLongitude() != null &&
               queryRealtimeDTO.getLatitude() != null) {
                //逆地址解析找到城市信息，然后从db找到城市信息
                AddressInfoResultBean addressInfoResultBean = gaodeUtil.regeo(queryRealtimeDTO.getLongitude(),
                        queryRealtimeDTO.getLatitude());
                City city =iCityService.queryCityDetail(addressInfoResultBean.getRegeocode().getAddressComponent().getProvince(),
                        addressInfoResultBean.getRegeocode().getAddressComponent().getCity(),
                        addressInfoResultBean.getRegeocode().getAddressComponent().getDistrict());
                if (null != city) {
                    cityId = city.getCityId();
                }
            }
        }

        //优先城市id
        if (StringUtils.isNotBlank(cityId)) {
            url = url + "&cityid=" +cityId;
        }else  {
            if (StringUtils.isNotBlank(queryRealtimeDTO.getCityName())) {
                url = url + "&city=" +queryRealtimeDTO.getCityName();
            }
        }

        res = HttpUtil.get(url);

        return JSON.parse(res);
    }



    @Override
    public Object querySevenDaysWeather(QuerySevenDTO querySevenDTO) {
        if (null == querySevenDTO) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_PARAM);
        }

        String url = PyodConstant.WeatherApi.SERVEN_DAYS_URL
                .replace("{appid}", weatherConfig.getAppId())
                .replace("{appsecret}", weatherConfig.getAppSecret());

        String res = "";
        String cityId = "";
        if (StringUtils.isNotBlank(querySevenDTO.getCityId())) {
            cityId =  querySevenDTO.getCityId();
        }else {
            if (querySevenDTO.getLongitude() != null &&
                    querySevenDTO.getLatitude() != null) {
                //逆地址解析找到城市信息，然后从db找到城市信息
                AddressInfoResultBean addressInfoResultBean = gaodeUtil.regeo(querySevenDTO.getLongitude(),
                        querySevenDTO.getLatitude());
                City city =iCityService.queryCityDetail(addressInfoResultBean.getRegeocode().getAddressComponent().getProvince(),
                        addressInfoResultBean.getRegeocode().getAddressComponent().getCity(),
                        addressInfoResultBean.getRegeocode().getAddressComponent().getDistrict());
                if (null != city) {
                    cityId = city.getCityId();
                }
            }
        }

        //优先城市id
        if (StringUtils.isNotBlank(cityId)) {
            url = url + "&cityid=" +cityId;
        }else  {
            if (StringUtils.isNotBlank(querySevenDTO.getCityName())) {
                url = url + "&city=" +querySevenDTO.getCityName();
            }
        }

        res = HttpUtil.get(url);

        return JSON.parse(res);
    }


    @Override
    public Object queryNationalPrecipitationWeather() {
        Object cacheWeatherData = iRedisService.getObj(RedisKeyEnum.WEATHER_NATIONAL_PRECIPITATION.getKey());
        if (null != cacheWeatherData) {
            return JSON.parse(cacheWeatherData.toString());
        }


        String url = PyodConstant.WeatherApi.PRECIPITATION_URL
                .replace("{appid}", weatherConfig.getAppId())
                .replace("{appsecret}", weatherConfig.getAppSecret());

        String res = HttpUtil.get(url);
        //降水量数据缓存一天
        iRedisService.setObj(RedisKeyEnum.WEATHER_NATIONAL_PRECIPITATION.getKey(),res, DateUtil.getSecondsNextEarlyMorning());
        return JSON.parse(res);
    }

}
