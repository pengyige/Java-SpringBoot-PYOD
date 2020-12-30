package top.yigege.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import top.yigege.service.IRedisService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisServiceImpl
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月28日 16:22
 */
@Service
public class RedisServiceImpl  implements IRedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void setObj(final String key, Object obj, long timeout) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, obj, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void setObj(final String key, Object obj) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, obj);
    }

    @Override
    public Object getObj(final String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o;
    }
}