package top.yigege.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SignUtil
 * @Description:请求签名工具栏
 * *1. 将所有参数（除了sign字段外）通过key1=value1&key2=value2拼接成字符串  (按key的升序拼接)
 * *2. 将拼接字符串进行base64编码
 * *3. 将编码后的值采用hmacSha1算法求哈希(openId作为key)
 * *4. 将哈希值进行md5加密
 * @author: yigege
 * @date: 2020年12月07日 14:30
 */
public class SignUtil {

    /**
     * 获取sign
     *
     * @param param
     * @param openId
     * @return
     */
    public static String getSign(Map<String, Object> param, String openId) {
        List<String> ss = new ArrayList<>();
        param.forEach((k, v) -> ss.add(k + "=" + v));
        String s = Base64.encode(StringUtils.join(ss, "&"));
        String sign = SecureUtil.md5().digestHex(SecureUtil.hmacSha1(openId).digest(s));
        return sign;
    }

}
