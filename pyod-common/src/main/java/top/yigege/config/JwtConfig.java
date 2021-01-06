package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: JwtConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 16:21
 */
@Data
@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.expire}")
    int expire;
}
