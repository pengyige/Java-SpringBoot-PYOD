package top.yigege.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: WebLog
 * @Description:用于web层 请求参数、请求时间日志标识
 * @author: yigege
 * @date: 2020年09月20日 13:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    String value() default "" ;
}

