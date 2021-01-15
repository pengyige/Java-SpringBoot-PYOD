package top.yigege.shiro;

import cn.hutool.core.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yigege.config.EnvConfig;
import top.yigege.filter.PermFailFilter;
import top.yigege.shiro.*;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月04日 14:06
 */
@Configuration
public class ShiroConfig {

    @Autowired
    EnvConfig envConfig;

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //TODO 自定义拦截器 待验证
        //接口未授权处理
        // shiroFilterFactoryBean.getFilters().put("perms", new PermFailFilter());

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/un-authorized");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /* filterChainDefinitionMap.put("/role/**", "authc");
        filterChainDefinitionMap.put("/user/**", "authc");
        filterChainDefinitionMap.put("/permission/**", "authc");*/
        filterChainDefinitionMap.put("/", "authc");
        filterChainDefinitionMap.put("/web", "authc");
        filterChainDefinitionMap.put("/web/**", "authc");
        filterChainDefinitionMap.put("/index.html", "authc");

        //TODO 先放开
        /*if (!envConfig.isDev()) {
             filterChainDefinitionMap.put("/swagger-ui.html", "authc");
             filterChainDefinitionMap.put("/doc.html", "authc");
             filterChainDefinitionMap.put("/swagger-ui.html", "authc");
             filterChainDefinitionMap.put("/webjars/**", "authc");
             filterChainDefinitionMap.put("/v2/**", "authc");
             filterChainDefinitionMap.put("/swagger-resources/**", "authc");
        }*/


        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        return defaultSecurityManager;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200000);
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }
}
