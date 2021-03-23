package top.yigege.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RabbitMQConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2021年03月07日 15:27
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 延迟交换机
     */
    public static final String DELAY_EXCHANGE_NAME = "delay.queue.exchange";

    /**
     * 赠送优惠券延迟队列(一天)
     */
    public static final String DELAY_QUEUEA_GIVE_COUPON_NAME = "delay.queue.give.coupon.queue";

    /**
     * 豆豆清空延迟队列(一年)
     */
    public static final String DELAY_QUEUEA_PEA_CLEAR_NAME = "delay.queue.pea.clear.queue";

    /**
     * 用户生日延迟队列(一年)
     */
    public static final String DELAY_QUEUEA_USER_BIRTHDAY_NAME = "delay.queue.user.birthday.queue";

    /**
     * 赠送优惠券路由key
     */
    public static final String DELAY_QUEUEA_GIVE_COUPON_ROUTING_KEY = "delay.queue.give.coupon.routingkey";


    /**
     * 豆豆清空路由key
     */
    public static final String DELAY_QUEUEA_PEA_CLAER_ROUTING_KEY = "delay.queue.pea.clear.routingkey";

    /**
     * 用户生日路由key
     */
    public static final String DELAY_QUEUEA_USER_BIRTHDAY_ROUTING_KEY = "delay.queue.user.birthday.routingkey";


    /**
     * 死信交换机
     */
    public static final String DEAD_LETTER_EXCHANGE = "dead.queue.deadletter.exchange";

    /**
     * 赠送优惠券死信队列(一天)
     */
    public static final String DEAD_QUEUEA_GIVE_COUPON_NAME = "dead.queue.give.coupon.queue";

    /**
     * 豆豆清空死信队列(一年)
     */
    public static final String DEAD_QUEUEA_PEA_CLEAR_NAME = "dead.queue.pea.clear.queue";

    /**
     * 用户生日死信队列(一年)
     */
    public static final String DEAD_QUEUEA_USER_BIRTHDAY_NAME = "dead.queue.user.birthday.queue";


    /**
     *  声明延时Exchange
     */
    @Bean("delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }


    /**
     *   声明死信Exchange
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明赠送优惠券延时队列
     * @return
     */
    @Bean("delayQueueForGiveCoupon")
    public Queue delayQueueForGiveCoupon(){
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DELAY_QUEUEA_GIVE_COUPON_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        //TODO 测试10s 正式86400
        args.put("x-message-ttl", 86400000);
        return QueueBuilder.durable(DELAY_QUEUEA_GIVE_COUPON_NAME).withArguments(args).build();
    }


    /**
     * 声明豆豆清空延时队列
     * @return
     */
    @Bean("delayQueueForPeaClear")
    public Queue delayQueueForPeaClear(){
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DELAY_QUEUEA_PEA_CLAER_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        //TODO 测试 正式一年
        args.put("x-message-ttl", 31536000*1000);
        return QueueBuilder.durable(DELAY_QUEUEA_PEA_CLEAR_NAME).withArguments(args).build();
    }

    /**
     * 用户生日延时队列
     * @return
     */
    @Bean("delayQueueForUserBirthday")
    public Queue delayQueueForUserBirthday(){
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DELAY_QUEUEA_USER_BIRTHDAY_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        //TODO 测试3600s 正式一年31536000
        args.put("x-message-ttl",31536000 *1000);
        return QueueBuilder.durable(DELAY_QUEUEA_USER_BIRTHDAY_NAME).withArguments(args).build();
    }

    /**
     * 声明赠送优惠券死信队列
     * @return
     */
    @Bean("deadLetterQueueForGiveCoupon")
    public Queue deadLetterQueueForGiveCoupon(){
        return new Queue(DEAD_QUEUEA_GIVE_COUPON_NAME);
    }

    /**
     * 声明豆豆清空死信队列
     * @return
     */
    @Bean("deadLetterQueueForPeaClear")
    public Queue deadLetterQueueForPeaClear(){
        return new Queue(DEAD_QUEUEA_PEA_CLEAR_NAME);
    }

    /**
     * 声明用户生日死信队列
     * @return
     */
    @Bean("deadLetterQueueForUserBirthday")
    public Queue deadLetterQueueForUserBirthday(){
        return new Queue(DEAD_QUEUEA_USER_BIRTHDAY_NAME);
    }


    /**
     * 绑定赠送礼物延迟队列与延迟交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding delayBindingForGiveCoupon(@Qualifier("delayQueueForGiveCoupon") Queue queue,
                                 @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_GIVE_COUPON_ROUTING_KEY);
    }

    /**
     * 绑定清空豆豆延迟队列与延迟交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding delayBindingForPeaClear(@Qualifier("delayQueueForPeaClear") Queue queue,
                                 @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_PEA_CLAER_ROUTING_KEY);
    }

    /**
     * 绑定用户生日队列与延迟交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding delayBindingForUserBirthday(@Qualifier("delayQueueForUserBirthday") Queue queue,
                                 @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_USER_BIRTHDAY_ROUTING_KEY);
    }


    /**
     * 绑定赠送优惠券死信队列与死信交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingForGiveCoupon(@Qualifier("deadLetterQueueForGiveCoupon") Queue queue,
                                               @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_GIVE_COUPON_ROUTING_KEY);
    }

    /**
     * 绑定豆豆清空死信队列与死信交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingForPeaClear(@Qualifier("deadLetterQueueForPeaClear") Queue queue,
                                               @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_PEA_CLAER_ROUTING_KEY);
    }

    /**
     * 绑定用户生日死信队列与死信交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingForUserBirthday(@Qualifier("deadLetterQueueForUserBirthday") Queue queue,
                                               @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_USER_BIRTHDAY_ROUTING_KEY);
    }

}
