package top.yigege.controller.city;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.city.AddCityDTO;
import top.yigege.dto.modules.city.ModifyCityDTO;
import top.yigege.dto.modules.city.QueryCityPageListDTO;
import top.yigege.model.City;
import top.yigege.service.ICityService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 城市 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "城市(WEB)")
@RestController
@RequestMapping("/web/city")
@Validated
public class WebCityController {

    @Autowired
    ICityService iCityService;

    @ApiOperation("添加城市")
    @PostMapping("/addCity")
    public ResultBean addCity(@Valid AddCityDTO addCityDTO){
        City city = new City();
        BeanUtil.copyProperties(addCityDTO, city);

        return ApiResultUtil.success(iCityService.save(city));
    };

    @ApiOperation("修改城市")
    @PostMapping("/modifyCity")
    public ResultBean modifyCity(@Valid ModifyCityDTO modifyCityDTO) {
        City City = new City();
        BeanUtil.copyProperties(modifyCityDTO, City);

        return ApiResultUtil.success(iCityService.updateById(City));
    }

    @ApiOperation("删除城市")
    @PostMapping("/deleteCityByIds")
    public ResultBean deleteCityByIds(@NotBlank(message = "城市id不能为空") String cityIds) {
        return ApiResultUtil.success(iCityService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(cityIds))));
    }

    @ApiOperation("查询城市分页列表")
    @PostMapping("/queryCityPageList")
    public LayuiTableResultBean queryCityPageList(QueryCityPageListDTO queryCityPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCityService.queryCityPageList(queryCityPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询城市详情")
    @PostMapping("/queryCityDetail")
    public ResultBean queryCityDetail(@NotNull(message = "城市id不能为空") Long cityId) {
        return ApiResultUtil.success(iCityService.getById(cityId));
    }

}
