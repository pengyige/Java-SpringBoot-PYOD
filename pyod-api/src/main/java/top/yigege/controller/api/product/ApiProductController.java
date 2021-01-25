package top.yigege.controller.api.product;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.service.IProductService;
import top.yigege.service.ISysUserService;
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
@RequestMapping("/api/product")
public class ApiProductController {

    @Autowired
    WeixinUtil weixinUtil;

    @Autowired
    IProductService iProductService;

    @Autowired
    ISysUserService iSysUserService;

    @ApiOperation("购买")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "productId", value = "商品id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "vipCardId", value = "会员卡id", required = true, dataType = "int")
    })
    @PostMapping("/buy")
    public ResultBean buy(@NotNull(message = "商品id不能为空") Long productId,
                         @NotNull(message = "会员卡不能为空") Long vipCardId,
                          @RequestAttribute Long userId) {
        return ApiResultUtil.success(iProductService.buy(productId,userId,vipCardId));
    }

    @ApiOperation("查询商品订单支付状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderNo", value = "订单编号", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "merchantId", value = "商户id", required = true, dataType = "string")
    })
    @PostMapping("/queryOrderStatus")
    public ResultBean queryOrderStatus(@NotNull(message = "商户id不能为空") Long merchantId,
                                       @NotBlank(message = "订单编号不能为空") String orderNo) throws Exception {
        return ApiResultUtil.success(weixinUtil.queryOrderStatus(iSysUserService.queryWxConfigByMerchantId(merchantId),orderNo));
    }
}
