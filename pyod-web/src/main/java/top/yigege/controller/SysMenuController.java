package top.yigege.controller;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import top.yigege.dto.modules.sysMenu.AddSysMenuDTO;
import top.yigege.dto.modules.sysMenu.ModifySysMenuDTO;
import top.yigege.model.SysMenu;

import top.yigege.service.ISysMenuService;
import top.yigege.util.ApiResultUtil;
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
@RequestMapping("/web/sysMenu")
public class SysMenuController {

    @Resource
    ISysMenuService iMenuService;

    @ApiOperation(value = "获取所有菜单")
    @PostMapping("/queryTreeMenu")
    public  List<LayuiTreeBean> queryTreeMenu() {
        List<LayuiTreeBean> menuList = iMenuService.queryTreeMenu();
        return menuList;
    }


    @PostMapping("/addMenu")
    public ResultBean addOrUpdateMenu(@Valid  AddSysMenuDTO addSysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(addSysMenuDTO,sysMenu);
        iMenuService.save(sysMenu);
        return ApiResultUtil.success(sysMenu);
    }


    @PostMapping("/modifyMenu")
    public ResultBean<SysMenu> modifyMenu(@Validated  ModifySysMenuDTO modifySysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(modifySysMenuDTO,sysMenu);
        iMenuService.updateById(sysMenu);
        return ApiResultUtil.success(sysMenu);
    }


    @ApiOperation(value = "查询菜单详情", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "menuId", value = "菜单id", required = true, dataType = "Int")
          }
    )
    @PostMapping("/queryMenuDetail")
    public ResultBean queryMenuDetail(@NotNull(message = "菜单id不能为空") Integer menuId) {
        return ApiResultUtil.success(iMenuService.getById(menuId));
    }

    @ApiOperation(value = "删除菜单", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "菜单id", required = true, dataType = "Int")
    }
    )
    @PostMapping("/deleteMenuById")
    public ResultBean deleteMenuById(@NotNull(message = "菜单id不能为空") Integer menuId) {
        return ApiResultUtil.success(iMenuService.removeById(menuId));
    }


}
