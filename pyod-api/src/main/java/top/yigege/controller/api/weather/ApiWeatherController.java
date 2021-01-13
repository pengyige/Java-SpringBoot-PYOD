package top.yigege.controller.api.weather;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.weather.QueryRealtimeDTO;
import top.yigege.dto.modules.weather.QuerySevenDTO;
import top.yigege.service.IWeatherService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

/**
 * @ClassName: WeatherController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 12:12
 */
@RestController
@RequestMapping("/api/weather")
@Validated
public class ApiWeatherController {

    @Autowired
    IWeatherService iWeatherService;

    @PostMapping("/queryRealTimeWeather")
    public ResultBean queryRealTimeWeather(QueryRealtimeDTO queryRealtimeDTO) {
        return  ApiResultUtil.success(iWeatherService.queryRealtimeWeather(queryRealtimeDTO));
    }

    @PostMapping("/querySevenDaysWeather")
    public ResultBean querySevenDaysWeather(QuerySevenDTO querySevenDTO) {
        return  ApiResultUtil.success(iWeatherService.querySevenDaysWeather(querySevenDTO));
    }

    @PostMapping("/queryNationalPrecipitationWeather")
    public ResultBean queryNationalPrecipitationWeather() {
        return  ApiResultUtil.success(iWeatherService.queryNationalPrecipitationWeather());
    }

}
