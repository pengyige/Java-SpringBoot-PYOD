package top.yigege.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.yigege.config.EnvConfig;
import top.yigege.config.JwtConfig;
import top.yigege.config.RequestConfig;
import top.yigege.config.SignConfig;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.service.ITokenService;
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

    @Autowired
    EnvConfig envConfig;

    @Resource
    ITokenService iTokenService;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    SignConfig signConfig;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, Object> params = new TreeMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        String url = req.getRequestURI();
        log.info("url:{}",url);

        Object tokenParam = params.get(PyodConstant.ApiRequestCommonParam.TOKEN);
        String token = "";
        if (null != tokenParam) {
            token = tokenParam.toString();
        }

        if (envConfig.isDev()) {
            //TODO 开发环境下不需要签名和验证token，需指定token
            if (StringUtils.isNotBlank(token)) {
                req.setAttribute(PyodConstant.JWT.USER_ID, iTokenService.getUserId(token));
            }else {
                req.setAttribute(PyodConstant.JWT.USER_ID, 1);
            }
            return true;
        }

        //接口文档地址不需要签名
        if (!envConfig.isDev() && signConfig.isEnable() && !url.equals("/pyod/api/doc")) {
           //签名校验
           if (params.get(PyodConstant.ApiRequestCommonParam.TIMESTAMP) == null
                   || params.get(PyodConstant.ApiRequestCommonParam.NONCE) == null
                   || params.get(PyodConstant.ApiRequestCommonParam.SIGN) == null
                   || params.get(PyodConstant.ApiRequestCommonParam.TOKEN) == null
                   || params.get(PyodConstant.ApiRequestCommonParam.MERCHANT_ID) == null
           ) {
               write(resp, ApiResultUtil.custom(ResultCodeEnum.SIGN_ERROR));
               return false;
           }
           // 校验sign
           String sign = params.get(PyodConstant.ApiRequestCommonParam.SIGN).toString();
           params.remove(PyodConstant.ApiRequestCommonParam.SIGN);
           String key = signConfig.getSecret();
           if (StringUtils.isNotBlank(token)) {
               key = token;
           }
           String checkSign = SignUtil.getSign(params,key);
           if (!sign.equals(checkSign)) {
               write(resp, ApiResultUtil.custom(ResultCodeEnum.SIGN_ERROR));
               return false;
           }
       }

        //登入需要校验的接口
        if (!requestConfig.getExcludeUrl().contains(url)) {
            // 校验token
            if (iTokenService.checkToken(token)) {
                req.setAttribute(PyodConstant.JWT.USER_ID, iTokenService.getUserId(token));
            }
        }

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
