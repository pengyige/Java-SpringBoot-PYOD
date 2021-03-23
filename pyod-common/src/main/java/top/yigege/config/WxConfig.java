package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WxConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2020年12月08日 10:46
 */
@Data
@Component
public class WxConfig {

    //@Value("${wx.appId}")
    private String appId;

    //@Value("${wx.secret}")
    private String secret;

    //@Value("${wx.mchId}")
    private String mchId;

    //@Value("${wx.mchKey}")
    private String mchKey;

    //@Value("${wx.notify.domain}")
    private String notifyUrl;

    private String templateId;
}
