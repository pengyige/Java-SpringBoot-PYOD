package top.yigege.controller.api.shop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.shop.QueryNearbyLatestShopDTO;
import top.yigege.dto.modules.shop.QueryNearbyShopPageListDTO;
import top.yigege.dto.modules.shop.QueryShopDetailDTO;
import top.yigege.dto.modules.userVipCard.BindUserVipCardDTO;
import top.yigege.model.Shop;
import top.yigege.service.IShopService;
import top.yigege.service.IUserVipCardService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.GetJuLiUtils;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;

/**
 * @ClassName: ApiVipCardController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月13日 21:27
 */
@Api(tags = "API-店铺相关请求")
@RestController
@RequestMapping("/api/shop")
@Validated
public class ApiShopController {

    @Autowired
    IShopService iShopService;

    @ApiOperation("查询最近的店铺信息")
    @PostMapping("/queryNearbyLatestShop")
    public ResultBean queryNearbyLatestShop(@Valid  QueryNearbyLatestShopDTO queryNearbyLatestShopDTO){

        return ApiResultUtil.success(iShopService.queryNearbyLatestShop(queryNearbyLatestShopDTO));
    }

    @ApiOperation("查询附近店铺信息")
    @PostMapping("/queryNearbyShopPageList")
    public ResultBean queryNearbyShopPageList(@Valid QueryNearbyShopPageListDTO queryNearbyLatestShopDTO){
        return ApiResultUtil.success(iShopService.queryNearbyShopPageList(queryNearbyLatestShopDTO));
    }



    @ApiOperation("查询店铺详情信息")
    @PostMapping("/queryShopDetail")
    public ResultBean queryShopDetail(QueryShopDetailDTO queryShopDetailDTO) {
        Shop shop = iShopService.getById(queryShopDetailDTO.getShopId());
        if (null != shop && null != queryShopDetailDTO.getLatitude() && null != queryShopDetailDTO.getLongitude()) {
            shop.setDistance(GetJuLiUtils.getDistance(queryShopDetailDTO.getLatitude(),
                    queryShopDetailDTO.getLongitude(),
                    shop.getLatitude(),
                    shop.getLongitude()));
        }
        return ApiResultUtil.success(shop);
    }

}
