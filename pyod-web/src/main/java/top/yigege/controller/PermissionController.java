package top.yigege.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.Permission;
import top.yigege.model.Role;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IPermissionService;
import top.yigege.service.IRoleService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
@Api(tags = "权限相关接口", description = "提供权限相关API")
@RestController
@RequestMapping("/permission")
@Validated
public class PermissionController {

    @Resource
    IPermissionService iPermissionService;


    @ApiOperation(value = "添加权限", notes = "添加一个新的权限", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = true, dataType = "String"),
    })
    @PostMapping("/addPermission")
    public ResultBean addPermission(@Valid @ApiIgnore Permission permission) {
        iPermissionService.save(permission);
        return ApiResultUtil.success(permission);
    }

}
