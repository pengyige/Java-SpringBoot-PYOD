package top.yigege.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import top.yigege.config.WxConfig;
import top.yigege.constant.PyodConstant;
import top.yigege.vo.wx.Code2SessionResultBean;
import top.yigege.vo.wx.WxPayInfoBean;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static com.github.wxpay.sdk.WXPayUtil.generateSignature;
import static com.github.wxpay.sdk.WXPayUtil.mapToXml;
import static com.github.wxpay.sdk.WXPayUtil.xmlToMap;
import static top.yigege.constant.PyodConstant.WeiXin.ORDER_QUERY_URL;

/**
 * @ClassName: WeixinUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年12月29日 10:48
 */
@Component
@Slf4j
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


    /**
     * 获取sessionBean
     * @param code
     * @return
     */
    public Code2SessionResultBean getCode2Session(String code) {
        String wxRequestUrl = "";
        wxRequestUrl = PyodConstant.WeiXin.CODE2SESSION_URL.replace("{appId}", wxConfig.getAppId())
                .replace("{secret}", wxConfig.getSecret())
                .replace("{js_code}",code);

        String getRes = HttpUtil.get(wxRequestUrl);

        return JSONUtil.toBean(getRes,Code2SessionResultBean.class);
    }

    /**
     * 统一下单
     * @param body
     * @param outTradeNo
     * @param totalFee
     * @param openId
     * @return
     * @throws Exception
     */
    public WxPayInfoBean getPrePayInfo(String body,String outTradeNo,Double totalFee,String openId) throws Exception {
        WxPayInfoBean wxPayInfoBean = new WxPayInfoBean();

        Map<String, String> map = Maps.newHashMap();
        map.put("appid", wxConfig.getAppId());
        map.put("mch_id",wxConfig.getMchId());
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("body",body);
        map.put("out_trade_no",outTradeNo);
        map.put("total_fee", totalFee+"");
        map.put("spbill_create_ip", IpUtil.getLocalIP());

        map.put("trade_type","JSAPI");
        map.put("notify_url", wxConfig.getNotifyUrl());
        map.put("openid", openId);
        String unifiedorderUrl = PyodConstant.WeiXin.UNIFIED_ORDER_URL;
        String sign = generateSignature(map, wxConfig.getSecret());
        map.put("sign", sign);

        String xml = mapToXml(map);
        //请求微信统一下单接口
        String xmlStr = HttpUtil.post(unifiedorderUrl,  xml);
        log.info("wx pay result = {}",xmlStr);
        Map map1 = xmlToMap(xmlStr);

        String return_code = (String) map1.get("return_code");//返回状态码
        String result_code = (String) map1.get("result_code");//返回状态码
        String err_code = (String) map1.get("err_code");//返回状态码
        String err_code_des = (String) map1.get("err_code_des");//返回状态码
        if (return_code.equals("SUCCESS") || return_code.equals(result_code)) {
            String prepay_id = (String) map1.get("prepay_id");//返回的预付单信息

            wxPayInfoBean.setAppId(wxConfig.getAppId());
            wxPayInfoBean.setTimeStamp(System.currentTimeMillis()+"");
            wxPayInfoBean.setNonceStr(WXPayUtil.generateNonceStr());
            wxPayInfoBean.setSignType("MD5");
            wxPayInfoBean.setWxPackage("prepay_id=" + prepay_id);
            Map<String,String> payMap = Convert.convert(HashMap.class,wxPayInfoBean);
            String paySign = generateSignature(payMap, wxConfig.getSecret());
            wxPayInfoBean.setPaySign(paySign);
            wxPayInfoBean.setPrepayId(prepay_id);

        }
        return wxPayInfoBean;
    }


    /**
     * 支付成功
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    public boolean queryOrderStatus(String outTradeNo) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        map.put("appid", wxConfig.getAppId());
        map.put("mch_id",wxConfig.getMchId());
        map.put("out_trade_no",outTradeNo);
        map.put("nonce_str", WXPayUtil.generateNonceStr());

        map.put("sign",generateSignature(map, wxConfig.getSecret()));
        String xmlStr = HttpUtil.post(ORDER_QUERY_URL,   mapToXml(map));
        log.info("wx queryOrderStatus result = {}",xmlStr);

        Map resultMap = xmlToMap(xmlStr);
        String tradeState =  (String)resultMap.get("trade_state");
        if (tradeState.equals("SUCCESS")) {
            return true;
        }

        return false;
    }
}
