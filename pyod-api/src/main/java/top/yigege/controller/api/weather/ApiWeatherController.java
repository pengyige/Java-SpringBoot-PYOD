package top.yigege.controller.api.weather;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "API-天气模块")
public class ApiWeatherController {

    @Autowired
    IWeatherService iWeatherService;

    @ApiOperation("查询实时天气")
    @PostMapping("/queryRealTimeWeather")
    public ResultBean queryRealTimeWeather(QueryRealtimeDTO queryRealtimeDTO) {
        return  ApiResultUtil.success(iWeatherService.queryRealtimeWeather(queryRealtimeDTO));
    }

    @ApiOperation("查询七日天气")
    @PostMapping("/querySevenDaysWeather")
    public ResultBean querySevenDaysWeather(QuerySevenDTO querySevenDTO) {
        return  ApiResultUtil.success(iWeatherService.querySevenDaysWeather(querySevenDTO));
    }

    @ApiOperation("查询全国降水量")
    @PostMapping("/queryNationalPrecipitationWeather")
    public ResultBean queryNationalPrecipitationWeather() {
        return  ApiResultUtil.success(iWeatherService.queryNationalPrecipitationWeather());
    }

}
