package top.yigege.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.annotation.WebLog;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName: HelloPyodController
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月17日 16:32
 */
@RestController
@RequestMapping("/hello-pyod")
@Validated
public class HelloPyodController {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloPyodController.class);

    /**
     * test 请求路径参数
     * @param userId
     */
    @WebLog
    @PostMapping("/test/{userId}")
    public void test(@PathVariable @NotBlank String userId) {
        LOGGER.info("test");
    }

    /**
     * 查询参数@RequestParam 表达提交
     * @param userid
     * @return
     */
    @WebLog
    @PostMapping("/test/requestparam")//@RequestParam(name = "userid")
    public  @ResponseBody ResultBean testRequestParam( @NotBlank(message = "用户ID不能为空")   String userid) {
        return ApiResultUtil.success();
    }

}
