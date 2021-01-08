package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: GaodeConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:27
 */
@Component
@Data
public class GaodeConfig {

    @Value("${gaode.key}")
    String key;
}
