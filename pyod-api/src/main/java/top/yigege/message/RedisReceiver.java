package top.yigege.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisReceiver
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月12日 20:56
 */
@Component
@Slf4j
public class RedisReceiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info(new String(message.getBody()));
        log.info(new String(message.getChannel()));
    }
}
