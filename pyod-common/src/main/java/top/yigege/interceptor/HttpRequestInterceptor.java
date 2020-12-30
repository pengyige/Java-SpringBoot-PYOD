package top.yigege.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.yigege.config.EnvConfig;
import top.yigege.config.RequestConfig;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SignUtil;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName: HttpRequestInterceptor
 * @Description:请求拦截器
 * @author: yigege
 * @date: 2020年12月07日 15:49
 */
@Slf4j
@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestConfig requestConfig;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, Object> params = new TreeMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }

        String url = req.getRequestURI();

      /*  if (EnvConfig.isDev()) {
            //TODO 测试环境下需指定userId
            req.setAttribute("userId", 3);
            return true;
        }*/
        // 校验url
        if (requestConfig.getExcludeUrl().contains(url)) {
            return true;
        }
        // 校验参数
        if (params.get("timestamp") == null
                || params.get("nonce") == null
                || params.get("sign") == null
                || params.get("userType") == null
        ) {
            write(resp, ApiResultUtil.custom(ResultCodeEnum.SIGN_ERROR));
            return false;
        }
        // 校验sign
        String openId = params.get("openId").toString();
        String sign = params.get("sign").toString();
        params.remove("sign");
        String checkSign = SignUtil.getSign(params, openId);
        if (!sign.equals(checkSign)) {
            write(resp, ApiResultUtil.custom(ResultCodeEnum.SIGN_ERROR));
            return false;
        }
        // 校验openId
        /*Integer userType = Integer.parseInt(params.get("userType").toString());
        UserEntity user = userService.checkLoginOpenId(openId, userType);
        if (null == user) {
            write(resp, R.code(RCode.OPENID_ERR));
            return false;
        }
        if (1 == user.getDisableFlag()) {
            write(resp, R.code(RCode.USER_DISABLE));
            return false;
        }
        req.setAttribute("userId", user.getUserId());*/

        return true;
    }


    /**
     * 响应数据
     *
     * @param response
     * @param resultBean
     * @throws IOException
     */
    private void write(HttpServletResponse response, ResultBean resultBean) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(resultBean));
        }
    }
}
