package top.yigege.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.yigege.constant.EnvType;

import javax.annotation.PostConstruct;

/**
 * @ClassName: EnvConfig
 * @Description:环境配置
 * @author: yigege
 * @date: 2020年12月07日 14:04
 */
@Component
public class EnvConfig {

    private static EnvConfig ref;
    @PostConstruct
    public void init(){
        ref = this;
    }

    @Value("${spring.profiles.active}")
    String ENV;

    /**
     * 是否是开发环境
     * @return true 开发人员自己的环境
     */
    public static boolean isDev() {
        return EnvType.DEV.getCode().equals(ref.ENV);
    }
}
