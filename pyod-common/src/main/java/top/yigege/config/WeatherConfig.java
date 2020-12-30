package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WeatherConfig
 * @Description:天气
 * @author: yigege
 * @date: 2020年12月30日 18:01
 */
@Data
@Component
public class WeatherConfig {

    @Value("${weather.appId}")
    private String appId;

    @Value("${weather.appSecret}")
    private String appSecret;
}
