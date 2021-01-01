package top.yigege.controller.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: NotFoundController
 * @Description:TODO 所有的异常已在全局异常处理，这里只可能是404
 * @author: yigege
 * @date: 2020年09月20日 20:22
 */
@Controller
public class NotFoundController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public ResultBean error(HttpServletRequest request) {
        return ApiResultUtil.notFound();
    }
}

