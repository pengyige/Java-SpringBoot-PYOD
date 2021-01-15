package top.yigege.filter;

/**
 * @ClassName: PermFailFilter
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月14日 20:54
 */

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 某阿里大牛
 * @Date: 2019/6/20 上午10:27
 */

public class PermFailFilter extends PermissionsAuthorizationFilter {



    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
            //跳转至登录页
            saveRequestAndRedirectToLogin(request, response);
        } else {
            //给前端提示无接口访问权限的错误码
            saveRequestAndReturnApiAccessError(request, response);
        }
        return false;
    }

    private void saveRequestAndReturnApiAccessError(ServletRequest request, ServletResponse response) {
        saveRequest(request);
        try {
            flushMsgStrToClient(response, ApiResultUtil.custom(ResultCodeEnum.NO_PERMISSION));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        try {
            flushMsgStrToClient(response, ApiResultUtil.custom(ResultCodeEnum.SESSION_EXPIRE));
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }


    public static void flushMsgStrToClient(ServletResponse response, Object object)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(object));
        response.getWriter().flush();
    }


}
