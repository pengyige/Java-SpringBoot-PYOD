package top.yigege.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yigege.filter.ValidateFilter;

import javax.servlet.DispatcherType;

/**
 * @ClassName: FilterConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月24日 16:40
 */
@Configuration
public class FilterConfig {

   /* @Bean
    public FilterRegistrationBean validateFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ValidateFilter());
        registration.addUrlPatterns("/*");
        registration.setName("validateFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }*/
}
