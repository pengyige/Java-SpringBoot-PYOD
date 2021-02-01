package top.yigege.controller.shop;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.shop.ModifyShopDTO;
import top.yigege.model.Shop;
import top.yigege.service.IShopService;
import top.yigege.service.ITokenService;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Autowired
    IShopService iShopService;

    @ApiOperation("通过二维码信息查询用户")
    @PostMapping("/queryQrCodeUserInfo")
    public ResultBean queryQrCodeUserInfo(@NotBlank(message = "内容不能为空") String content) {
        Long userId = iTokenService.getUserId(content);
        return ApiResultUtil.success(iUserService.getById(userId));
    }

    @ApiOperation("查询店铺详情")
    @PostMapping("/queryShopDetail")
    public ResultBean queryShopDetail(@NotNull(message = "店铺id不能为空") Long shopId) {
        return ApiResultUtil.success(iShopService.getById(shopId));
    }

    @ApiOperation("查询所有店铺列表")
    @PostMapping("/queryAllShopList")
    public ResultBean queryAllShopList() {
        return ApiResultUtil.success(iShopService.queryShopListByMerchantId(Long.valueOf(SessionUtil.getUser().getUserId())));
    }

    @ApiOperation("修改店铺信息")
    @PostMapping("/modifyShop")
    public ResultBean modifyShop(@Valid ModifyShopDTO modifyShopDTO) {
        Shop shop = new Shop();
        BeanUtil.copyProperties(modifyShopDTO,shop);
        iShopService.updateById(shop);
        return ApiResultUtil.success();
    }
}
