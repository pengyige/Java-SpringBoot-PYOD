package top.yigege.controller.common;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
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
@ApiIgnore
@RestController
@RequestMapping("/hello-pyod")
@Validated
@Slf4j
public class HelloPyodController {


    /**
     * test 请求路径参数
     * @param userId
     */
    @WebLog
    @PostMapping("/test/{userId}")
    public void test(@PathVariable @NotBlank String userId) {
        log.info("test");
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
