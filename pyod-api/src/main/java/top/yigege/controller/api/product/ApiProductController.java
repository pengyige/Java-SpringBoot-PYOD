package top.yigege.controller.api.product;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.service.IProductService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.WeixinUtil;
import top.yigege.vo.ResultBean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Api(tags = "API-商品模块")
@RestController
@RequestMapping("/product")
public class ApiProductController {

    @Autowired
    WeixinUtil weixinUtil;

    @Autowired
    IProductService iProductService;

    @ApiOperation("购买")
    @PostMapping("/buy")
    public ResultBean buy(@NotNull(message = "商品id不能为空") Long productId,
                          Long vipCardId,
                          Long cardCoverId,
                          @RequestAttribute Long userId) {
        return ApiResultUtil.success(iProductService.buy(productId,userId,vipCardId,cardCoverId));
    }

    @ApiOperation("查询商品订单支付状态")
    @PostMapping("/queryOrderStatus")
    public ResultBean queryOrderStatus(@NotBlank(message = "订单编号不能为空") String orderNo) throws Exception {
        return ApiResultUtil.success(weixinUtil.queryOrderStatus(orderNo));
    }
}
