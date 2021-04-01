package top.yigege.global;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.yigege.annotation.Cache;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @ClassName: CacheAspect
 * @Description:TODO
 * @author: yigege
 * @date: 2021年03月31日 17:40
 */
@Component
@Aspect
@Slf4j
public class CacheAspect {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 定义分隔符
     */
    private static final String DELIMITER = ":";
    /**
     * 定义锁后缀
     */
    private static final String LOCKSTR = "_LOCKSTR";
    private static final String REDIS_SOP = "REDIS_SOP";


    //@Pointcut("execution(* top.yigege..*.*(..))")
    @Pointcut("@annotation( top.yigege.annotation.Cache)")
    public void cachePointcut() {
    }

    @Around("cachePointcut()")
    public Object RedisCache(final ProceedingJoinPoint joinPoint)
            throws Throwable {
        String clazzName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String key = getKey(clazzName, methodName, args);
        log.info("生成key: " + key);
        Object value = getCachedData(key);
        Object result = null;
        if (null == value) {
            // 缓存未命中
            log.debug("缓存未命中");
            // 调用数据库查询方法
            synchronized (key + LOCKSTR) {
                value = getCachedData(key);
                if (null == value) {
                    result = joinPoint.proceed(args);
                }
            }
            // 序列化查询结果
            final String jsonResult = serialize(result);
            final int keyExpire = getKeyExpire(joinPoint);
            if (keyExpire == 0) {
                redisTemplate.opsForValue().set(key, jsonResult);
            } else {
                redisTemplate.opsForValue().set(key, jsonResult, Long.valueOf(keyExpire));
            }
            return result;
        }
        log.debug("缓存命中, value = " + value);

        result = JSON.parse((String) value);
        log.debug("反序列化结果 = {}" + result);

        return result;
    }

    private int getKeyExpire(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Class clazz = targetMethod.getClass();
        Cache cache = targetMethod.getAnnotation(Cache.class);
        int expire = cache.expire();
        return expire;
    }

    private Object getCachedData(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (null == value) {
            value = redisTemplate.opsForValue().get(key);
        }
        return value;
    }

    private String getKey(String clazzName, String methodName, Object[] args) {
        StringBuilder key = new StringBuilder(REDIS_SOP);
        key.append(clazzName);
        key.append(DELIMITER);
        key.append(methodName);
        key.append(DELIMITER);
        if (args.length > 0) {
            for (Object obj : args) {
                if (obj != null) {
                    key.append(obj.toString());
                    key.append(DELIMITER);
                }
            }
        }
        return key.toString();
    }

    protected String serialize(Object target) {
        if (target == null) {
            return "";
        }
        return JSON.toJSONString(target);
    }

}
