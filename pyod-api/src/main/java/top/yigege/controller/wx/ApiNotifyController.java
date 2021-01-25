package top.yigege.controller.wx;

import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.config.WxConfig;
import top.yigege.model.PurchaseHistory;
import top.yigege.service.IProductService;
import top.yigege.service.IShopService;
import top.yigege.service.impl.ShopServiceImpl;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ApiNotifyController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月16日 19:48
 */
@Api(tags ="API-微信回调通知")
@Controller
@Slf4j
@RequestMapping("/wx")
public class ApiNotifyController {

    @Autowired
    WxConfig wxConfig;

    @Autowired
    IProductService iProductService;

    @ApiOperation("通知")
    @PostMapping("/notify")
    public String wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map orderMap = new HashMap();
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String notityXml = sb.toString();
        String resXml = "";
        Map resPrint = new HashMap();
        Map<String, String> resultMap = WXPayUtil.xmlToMap(notityXml);
        String returnCode = (String) resultMap.get("return_code");//业务结果
        String orderNo = resultMap.get("out_trade_no");//订单号
        String sign = resultMap.get("sign");//获取微信签名
        resultMap.remove("sign");//去除签名字段
        String signNew = WXPayUtil.generateSignature(resultMap, wxConfig.getMchKey()); //重新签名
        log.info("wx pay notify：{}",resXml);
        if (signNew.equals(sign)) {
            if ("SUCCESS".equals(returnCode)) {
                //TODO 根据订单编号处理业务
                orderMap.put("orderNo", orderNo);
                resPrint.put("return_code", "SUCCESS");
                resPrint.put("return_msg", "ok");
                resXml = WXPayUtil.mapToXml(resPrint);

                iProductService.buyProductFinishHanlder(orderNo);

            } else {
                resPrint.put("return_code", "FAIL");
                resPrint.put("return_msg", "业务结果失败");
            }

        } else {
            resPrint.put("return_code", "FAIL");
            resPrint.put("return_msg", "签名失败");
            resXml = WXPayUtil.mapToXml(resPrint);
        }
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
        br.close();
        return null;
    }

    @ApiOperation("通知测试")
    @PostMapping("/wxNotifyTest")
    @ResponseBody
    public ResultBean wxNotifyTest(String orderNo) {
        iProductService.buyProductFinishHanlder(orderNo);
        return ApiResultUtil.success();
    }
}
