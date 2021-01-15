package top.yigege.controller;


import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yigege
 * @since 2020-11-02
 */
@Api(tags = "系统字典(WEB)")
@RestController
@RequestMapping("/web/sysDict")
@Validated
public class SysDictController {

}
