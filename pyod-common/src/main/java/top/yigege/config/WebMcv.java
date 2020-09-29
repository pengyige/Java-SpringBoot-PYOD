package top.yigege.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: WebMcv
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月25日 17:53
 */
@Configuration
public class WebMcv implements WebMvcConfigurer {
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        //设置启动默认访问index.html
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger资源映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        //默认找static目录
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
  }
}