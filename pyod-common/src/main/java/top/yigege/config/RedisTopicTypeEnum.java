package top.yigege.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: RedisKeyTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月12日 21:50
 */
@AllArgsConstructor
@Getter
public enum RedisTopicTypeEnum {

    CHANNEL_TEST("channel:test","测试通道");

    String topic;

    String desc;
}
