package top.yigege.vo.wx;

import lombok.Data;

/**
 * @ClassName: WxPayInfoBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月16日 19:33
 */
@Data
public class WxPayInfoBean {

    /**
     * APPID
     */
    String appId;

    /**
     * 时间戳
     */
    String timeStamp;

    /**
     * 随机字符串
     */
    String nonceStr;

    /**
     * 签名类型
     */
    String signType;

    /**
     * 支付包
     */
    String wxPackage;

    /**
     * 支付签名
     */
    String paySign;

    /**
     * 下单编号
     */
    String prepayId;

}
