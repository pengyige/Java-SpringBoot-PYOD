package top.yigege.controller;


import cn.hutool.crypto.digest.DigestUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.yigege.annotation.WebLog;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.Menu;
import top.yigege.model.User;
import top.yigege.service.IMenuService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.vo.LayuiTreeBean;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yigege
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    IMenuService iMenuService;

    @ApiOperation(value = "获取用户菜单")
    @PostMapping("/queryTreeMenu")
    public  List<LayuiTreeBean> queryTreeMenu() {
        List<LayuiTreeBean> menuList = iMenuService.queryTreeMenu();
        return menuList;
    }

    @ApiOperation(value = "添加或修改菜单", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "菜单id", required = false, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "pid", value = "父ID", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "菜单名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "url", value = "路径", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "状态", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = false, dataType = "String")}
            )
    @PostMapping("/addOrUpdateMenu")
    public ResultBean addOrUpdateMenu(@Valid @ApiIgnore Menu menu) {
        iMenuService.saveOrUpdate(menu);
        return ApiResultUtil.success(menu);
    }


    @ApiOperation(value = "查询菜单详情", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "菜单id", required = true, dataType = "Int")
          }
    )
    @PostMapping("/queryMenuDetail")
    public ResultBean queryMenuDetail(@NotNull Integer id) {
        return ApiResultUtil.success(iMenuService.getById(id));
    }

    @ApiOperation(value = "删除菜单", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "菜单id", required = true, dataType = "Int")
    }
    )
    @PostMapping("/deleteMenuById")
    public ResultBean deleteMenuById(@NotNull Integer id) {
        return ApiResultUtil.success(iMenuService.removeById(id));
    }


}
