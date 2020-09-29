package top.yigege.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: MybatisPlusConfig
 * @Description:配置分页插件
 * @author: yigege
 * @date: 2020年09月23日 16:44
 */
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
