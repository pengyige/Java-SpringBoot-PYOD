package top.yigege.service;


import cn.hutool.json.JSONObject;
import top.yigege.dto.modules.weather.QueryRealtimeDTO;
import top.yigege.dto.modules.weather.QuerySevenDTO;

/**
 * @ClassName: IWeatherService
 * @Description:天气服务
 * @author: yigege
 * @date: 2021年01月08日 14:06
 */
public interface IWeatherService {

    /**
     * 查询实时天气
     *
     * @param queryRealtimeDTO
     * @return
     */
    Object queryRealtimeWeather(QueryRealtimeDTO queryRealtimeDTO);

    /**
     * 查询七日天气
     *
     * @param querySevenDTO
     * @return
     */
    Object querySevenDaysWeather(QuerySevenDTO querySevenDTO);


    /**
     * 查询全国降水量预报
     *
     * @return
     */
    public Object queryNationalPrecipitationWeather();

}
