package top.yigege.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import top.yigege.config.WxConfig;
import top.yigege.constant.PyodConstant;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WeixinUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年12月29日 10:48
 */
@Component
public class WeixinUtil {

    @Autowired
    WxConfig wxConfig;

    /**
     * 获取微信token
     * @return
     */
    public String getToken() {
        String wxRequestUrl = "";
            wxRequestUrl = PyodConstant.WeiXin.GET_TOKEN_URL.replace("{appId}", wxConfig.getAppId())
                    .replace("{secret}", wxConfig.getSecret());

        String getRes = HttpUtil.get(wxRequestUrl);

        //获取ACCESS_TOKENdeleteHouseRoom
        JSONObject responseObj = (JSONObject) JSONObject.parse(getRes);
        return responseObj.getString("access_token");
    }

    /**
     * 获取微信二维码
     * @param page
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getQrCode(String page) throws UnsupportedEncodingException {
        String url = PyodConstant.WeiXin.GET_WX_ACODE.replace("{access_token}",getToken());
        Map requestParam = new HashMap();
        requestParam.put("path", page);
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity requestEntity = new HttpEntity(JSON.toJSONString(requestParam), headers);
        ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
        return Base64.encodeBase64String(entity.getBody());
    }

}
