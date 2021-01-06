package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SignConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月06日 14:15
 */
@Component
@Data
public class SignConfig {

    @Value("${sign.enable}")
    boolean enable;

    @Value("${sign.secret}")
    String secret;

}
