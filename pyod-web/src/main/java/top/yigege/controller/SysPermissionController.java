package top.yigege.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.yigege.constant.ResultCodeEnum;

import top.yigege.dto.modules.sysPermission.QuerySysPermissionPageListDTO;
import top.yigege.model.SysPermission;
import top.yigege.service.ISysPermissionService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
@Api(tags = "权限管理(WEB)", description = "提供权限相关API")
@RestController
@RequestMapping("/web/sysPermission")
@Validated
public class SysPermissionController {

    @Resource
    ISysPermissionService iPermissionService;


    @PostMapping("/addOrUpdatePermission")
    public ResultBean addOrUpdatePermission(@Valid @ApiIgnore SysPermission permission) {
        iPermissionService.saveOrUpdate(permission);
        return ApiResultUtil.success(permission);
    }

    @ApiOperation("查询所有权限")
    @PostMapping("/queryPermissionList")
    public LayuiTableResultBean queryPermissionList(QuerySysPermissionPageListDTO querySysPermissionPageListDTO) {
        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        List<SysPermission> permissionList = new ArrayList<>();
        try {
            LambdaQueryWrapper<SysPermission> queryWrapper = new  LambdaQueryWrapper<SysPermission>();
            if (null != querySysPermissionPageListDTO) {
                if (null != querySysPermissionPageListDTO.getPermissionId()) {
                    queryWrapper.eq(SysPermission::getPermissionId, querySysPermissionPageListDTO.getPermissionId());
                }

                if (StringUtils.isNotBlank(querySysPermissionPageListDTO.getName())) {
                    queryWrapper.eq(SysPermission::getName, querySysPermissionPageListDTO.getName());
                }
            }
            permissionList  = iPermissionService.list(queryWrapper);
        }catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, permissionList.size(), permissionList);
    }

    @ApiOperation("通过ID查询权限")
    @ApiImplicitParam(paramType = "query", name = "id", value = "权限id", required = true, dataType = "Int")
    @PostMapping("/queryPermissionDetail")
    public ResultBean queryPermissionDetail(@NotNull(message = "权限ID不能为空") Integer id) {
        return ApiResultUtil.success(iPermissionService.getById(id));
    }


    @ApiOperation("通过ID删除权限")
    @ApiImplicitParam(paramType = "query", name = "id", value = "权限id", required = true, dataType = "Int")
    @PostMapping("/deletePermissionById")
    public ResultBean deletePermissionById(@NotNull(message = "权限ID不能为空") Integer id) {
        return ApiResultUtil.success(iPermissionService.removeById(id));
    }

}
