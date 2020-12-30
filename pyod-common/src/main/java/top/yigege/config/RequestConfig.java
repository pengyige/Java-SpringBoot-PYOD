package top.yigege.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 请求配置
 *
 * @author lwl
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "request")
public class RequestConfig {

    private List<String> excludeUrl;
}
