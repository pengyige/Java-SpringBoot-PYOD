package top.yigege.controller.api.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.city.QueryCityDataResDTO;
import top.yigege.model.Level;
import top.yigege.service.IBannerService;
import top.yigege.service.ICardCoverService;
import top.yigege.service.ICityService;
import top.yigege.service.ILevelService;
import top.yigege.service.ILevelWelfareService;
import top.yigege.service.IProductService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: ApiCommonController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月13日 16:05
 */
@Api(tags = "API-公共数据请求")
@RestController
@RequestMapping("/api/common")
public class ApiCommonController {

    @Autowired
    IBannerService iBannerService;

    @Autowired
    ICardCoverService iCardCoverService;

    @Autowired
    ILevelService iLevelService;

    @Autowired
    ILevelWelfareService iLevelWelfareService;

    @Autowired
    ICityService iCityService;

    @Autowired
    IProductService iProductService;

    @ApiOperation("查询所有轮播列表")
    @PostMapping("/queryBannerList")
    public ResultBean queryBannerList(@NotNull(message = "商户ID不能为空") Long merchantId) {
        return ApiResultUtil.success(iBannerService.queryBannerByMerchantId(merchantId));
    }

    @ApiOperation("查询所有封面列表")
    @PostMapping("/queryCardCoverList")
    public ResultBean queryAllCardCoverList(@NotNull(message = "商户ID不能为空") Long merchantId) {
        return ApiResultUtil.success(iCardCoverService.queryCardCoverByMerchantId(merchantId));
    }

    @ApiOperation("查询默认的封面")
    @PostMapping("/queryDefaultCardCover")
    public ResultBean queryDefaultCardCover(@NotNull(message = "商户ID不能为空") Long merchantId) {
        return  ApiResultUtil.success(iCardCoverService.queryDefaultCardCover(merchantId));
    }

    @ApiOperation("查询所有等级列表")
    @PostMapping("/queryLevelList")
    public ResultBean queryLevelList(@NotNull(message = "商户ID不能为空") Long merchantId) {
        return ApiResultUtil.success(iLevelService.queryLevelByMerchantId(merchantId));
    }

    @ApiOperation("查询所有等级福利列表")
    @PostMapping("/queryLevelWelfareList")
    public ResultBean queryLevelWelfareList(@NotNull(message = "商户ID不能为空") Long merchantId) {
        List<Level> levelList = iLevelService.queryLevelByMerchantId(merchantId);
        levelList.forEach(item -> {
            item.setLevelWelfareList(iLevelWelfareService.queryLevelWelfareByLevelId(item.getLevelId()));
        });
        return ApiResultUtil.success(levelList);
    }

    @ApiOperation("查询所有城市数据列表")
    @PostMapping("/queryCityData")
    public ResultBean queryCityData() {
        QueryCityDataResDTO queryCityDataResDTO = new QueryCityDataResDTO();
        queryCityDataResDTO.setHotCityList(iCityService.queryHotCity());
        queryCityDataResDTO.setCityList(iCityService.queryAllCity());
        return ApiResultUtil.success(queryCityDataResDTO);
    }


    @ApiOperation("查询所有商品数据列表")
    @PostMapping("/queryProductData")
    public ResultBean queryProductData(@NotNull(message = "商户ID不能为空") Long merchantId) {
        return ApiResultUtil.success(iProductService.queryAllProductInfo(merchantId));
    }
}
