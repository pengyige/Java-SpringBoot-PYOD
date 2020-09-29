package top.yigege.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.yigege.service.IGenerateIDService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: GenerateIDServiceImpl
 * @Description:Redis 实现唯一ID
 * @author: yigege
 * @date: 2020年09月28日 17:36
 */
@Service
public class GenerateIDServiceImpl implements IGenerateIDService {

    private static final Long DELTA = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getNo(String businessFlag) {
        try {

            // 生成14位的时间戳(每秒使用新的时间戳当key)
            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
            // 获得redis-key
            String newKey = businessFlag + timeStamp;
            // 获取自增值（时间戳+自定义key）
            Long increment = redisTemplate.opsForValue().increment(newKey, DELTA);
            // 设置时间戳生成的key的有效期为2秒
            redisTemplate.expire(newKey, 1, TimeUnit.DAYS);
            // 获取编号 businessFlag+时间戳 + 唯一自增Id( 6位数,不过前方补0)
            return businessFlag + timeStamp + String.format("%06d", increment);
        } catch (Exception e) {
            // redis 宕机时采用时间戳加随机数
            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
            Random random = new Random();
            //8位时间戳到 + 6位随机数
            timeStamp +=(random.nextInt(10)+"") + (random.nextInt(10)+"") + (random.nextInt(10)+"");
            timeStamp +=(random.nextInt(10)+"") + (random.nextInt(10)+"") + (random.nextInt(10)+"");
            return businessFlag + timeStamp;
        }
    }
}
