package top.yigege.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.vo.ResultBean;

/**
 * @ClassName: WebConsoleController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月30日 19:04
 */
@Api(tags = "控制台(WEB)", description = "提供用户相关API")
@RestController
@RequestMapping("/web/console")
@Slf4j
@Validated
public class WebConsoleController {

    @Autowired
    IUserService iUserService;

    @ApiOperation("查询主页数据")
    @PostMapping("/queryHomeData")
    public ResultBean queryHomeData() {
        return ApiResultUtil.success(iUserService.queryHomeData(SessionUtil.getUser().getUserId()));
    }
}
