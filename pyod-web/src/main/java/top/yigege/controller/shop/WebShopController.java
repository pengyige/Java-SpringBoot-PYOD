package top.yigege.controller.shop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.service.ITokenService;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: WebShopController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月22日 22:58
 */
@Api(tags = "商家(WEB)")
@RestController
@RequestMapping("/web/shop")
@Validated
public class WebShopController {

    @Autowired
    IUserService iUserService;

    @Autowired
    ITokenService iTokenService;

    @ApiOperation("通过二维码信息查询用户")
    @PostMapping("/queryQrCodeUserInfo")
    public ResultBean queryQrCodeUserInfo(@NotBlank(message = "内容不能为空") String content) {
        Long userId = iTokenService.getUserId(content);
        return ApiResultUtil.success(iUserService.getById(userId));
    }
}
