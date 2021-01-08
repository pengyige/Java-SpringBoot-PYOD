package top.yigege.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigege.config.GaodeConfig;
import top.yigege.constant.PyodConstant;
import top.yigege.vo.gaode.AddressInfoResultBean;

/**
 * @ClassName: GaodeUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:42
 */
@Component
public class GaodeUtil {

    @Autowired
    GaodeConfig gaodeConfig;


    /**
     * 逆地址解析
     * @param longtitude
     * @param latitude
     * @return
     */
    public AddressInfoResultBean regeo(double longtitude, double latitude) {
        String url = PyodConstant.GaodeApi.REGEO_URL.replace("{key}", gaodeConfig.getKey());
        //经纬度
        String location = longtitude + "," + latitude;
        url += "&location="+location;
        String res = HttpUtil.get(url);
        AddressInfoResultBean addressInfoResultBean = JSONUtil.toBean(res, AddressInfoResultBean.class);
        return addressInfoResultBean;
    }

}
