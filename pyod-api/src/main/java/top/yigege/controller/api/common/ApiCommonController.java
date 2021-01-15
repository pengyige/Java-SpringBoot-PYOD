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
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

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

    @ApiOperation("查询所有轮播列表")
    @PostMapping("/queryBannerList")
    public ResultBean queryBannerList() {
        return ApiResultUtil.success(iBannerService.list());
    }

    @ApiOperation("查询所有封面列表")
    @PostMapping("/queryCardCoverList")
    public ResultBean queryAllCardCoverList() {
        return ApiResultUtil.success(iCardCoverService.list());
    }

    @ApiOperation("查询所有等级列表")
    @PostMapping("/queryLevelList")
    public ResultBean queryLevelList() {
        return ApiResultUtil.success(iLevelService.list());
    }

    @ApiOperation("查询所有等级福利列表")
    @PostMapping("/queryLevelWelfareList")
    public ResultBean queryLevelWelfareList() {
        List<Level> levelList = iLevelService.list();
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
}
