package top.yigege.global;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yigege.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: LogAspect
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月20日 13:13
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private static final ThreadLocal<Long> timeTreadLocal = new ThreadLocal<>();

    //@Pointcut("@annotation( top.yigege.annotation.WebLog)")
    @Pointcut("execution(* top.yigege.controller..*.*(..))")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        timeTreadLocal.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取请求的request
        HttpServletRequest request = attributes.getRequest();
        // 获取所有请求的参数，封装为map对象
        // Map<String,Object> parameterMap = getParameterMap(request);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取被拦截的方法
        Method method = methodSignature.getMethod();
        // 获取被拦截的方法名
        String methodName = method.getName();
        // 获取所有请求参数key和value
        //TODO 获取application/json

        //TODO 获取application/x-www-form-urlencoded;charset=UTF-8
        //String keyValue = JsonUtil.toJson(getParameterMap(request));
        // 请求类型
        String contentType = request.getHeader("Content-Type");
        String keyValue = "";
        if (StringUtils.isNotBlank(contentType) && (contentType.contains("application/x-www-form-urlencoded")
        || contentType.contains("multipart/form-data"))) {
            keyValue = JsonUtil.toJson(getParameterMap(request));
        }else {
            keyValue = getReqParameter(request);
        }


        String UUID = java.util.UUID.randomUUID().toString();
        // 执行方法
        Object result = joinPoint.proceed();

        long startTime = timeTreadLocal.get();
        double callTime = (System.currentTimeMillis() - startTime) / 1000.0;

        log.info("\nno:{}\n" +
                        "method:{}\n" +
                        "url:{}\n" +
                        "requestMethod:{}\n" +
                        "contentType:{}\n" +
                        "uri:{}\n" +
                        "param:{}\n"+
                        "result:{}\n"+
                        "time:{}s",
                UUID,
                method.getDeclaringClass() + "." + methodName + "()",
                request.getRequestURL().toString(),
                request.getMethod(),
                contentType,
                request.getRequestURI(),
                keyValue,
                JsonUtil.toJson(result),
                callTime);
        return result;

    }

    public String getReqParameter(HttpServletRequest request) throws IOException {
        if (request == null) {
            return null;
        } else {
            return JsonReq(request);
        }

    }

    /**
     * 获取所有请求参数，封装为map对象
     *
     * @return
     */
    public Map<String, Object> getParameterMap(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        StringBuilder stringBuilder = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            String keyValue = key + " : " + value + " ; ";
            stringBuilder.append(keyValue);
            parameterMap.put(key, value);
        }
        return parameterMap;
    }

    public String JsonReq(HttpServletRequest request) throws IOException {
        return "json  param print not support";
       /* BufferedReader br;
        StringBuilder sb = null;
        String reqBody = null;

        br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
        request.setAttribute("inputParam", reqBody);
        return reqBody;*/

    }
}
