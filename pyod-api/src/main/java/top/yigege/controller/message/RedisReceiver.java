package top.yigege.controller.message;

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
public class RedisReceiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(new String(message.getBody()));
        System.out.println(new String(message.getChannel()));
    }
}
