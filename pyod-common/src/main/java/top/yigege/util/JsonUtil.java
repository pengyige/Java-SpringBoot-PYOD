package top.yigege.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: JsonUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月24日 14:06
 */
public final class JsonUtil {

    private static final SerializeConfig SERIALIZE_CONFIG;
    private static final SerializerFeature[] SERIALIZER_FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    static {
        SERIALIZE_CONFIG = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        SERIALIZE_CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * 将对象转为json字符串
     *
     * @param object
     * @return
     */
    public static String toFeaturesJson(Object object) {
        return JSON.toJSONString(object, SERIALIZE_CONFIG, SERIALIZER_FEATURES);
    }

    /**
     * 将对象转为json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, SERIALIZE_CONFIG);
    }

    /**
     * 将json字符串转为Object实例
     *
     * @param json
     * @return
     */
    public static Object parse(String json) {
        return JSON.parse(json);
    }

    /**
     * 将json字符串转为指定类型的实例
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    /**
     * 将json转为Map
     *
     * @param json
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(String json) {
        return (Map<String, T>) JSONObject.parseObject(json);
    }

    /**
     * 将json转为指定类型的List
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

}
