package top.yigege.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.yigege.constant.ResultCodeEnum;

import top.yigege.model.SysRole;
import top.yigege.service.ISysRoleService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.LayuiTreeBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-27
 */
@Api(tags = "角色相关接口", description = "提供角色相关API")
@RestController
@RequestMapping("/web/sysRole")
@Validated
@Slf4j
public class SysRoleController {


    @Resource
    ISysRoleService iRoleService;


    @ApiOperation(value = "添加或更新角色", notes = "添加一个新的角色", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色id", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "角色名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = true, dataType = "String"),
    })
    @PostMapping("/addOrUpdateRole")
    public ResultBean addOrUpdateRole(@Valid @ApiIgnore SysRole role, String menuIds, String permissionIds) {

        SysRole dbRole = iRoleService.addOrUpdateRole(role,Utils.parseIntegersList(Utils.splitStringToList(menuIds)),
                Utils.parseIntegersList(Utils.splitStringToList(permissionIds)));

        return ApiResultUtil.success(dbRole);
    }

    @ApiOperation(value = "批量删除角色",  response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "角色ID,多个用,隔开", required = true, dataType = "String"),
    })
    @PostMapping("/deleteRoleByIds")
    public ResultBean deleteRoleByIds(@NotBlank(message = "角色ID不能为空") String id) {
        iRoleService.deleteRoleContainsRecord(Utils.parseIntegersList(Utils.splitStringToList(id)));
        return ApiResultUtil.success();
    }


    @ApiOperation("查询角色详情")
    @ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = true, dataType = "String")
    @PostMapping("/queryRoleDetail")
    public ResultBean queryRoleDetail(@NotNull  Integer id) {
        return ApiResultUtil.success(iRoleService.queryRoleInfo(id));
    }


    @ApiOperation("查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = false, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = false, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "角色名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "no", value = "编号", required = false, dataType = "String")

    })
    @PostMapping("/queryRoleList")
    public LayuiTableResultBean queryRoleList(int page,
                                              @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer id,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String no) {
        Map<String ,Object> paramMap = new HashMap<>();
        paramMap.put("role_id",id);
        paramMap.put("name",name);
        paramMap.put("role_no",no);

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iRoleService.queryRoleList(page,pageSize,paramMap);
        }catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
            log.error(e.getMessage(), e);
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }



    @ApiOperation(value = "绑定权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色ID", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "permissionId", value = "权限ID,多个用逗号分隔", required = true, dataType = "Int")
    })
    @PostMapping("/bindPermission}")
    public ResultBean bindPermission(@NotNull(message = "角色ID不能为空") Integer roleId,
                               @NotNull(message = "权限ID不能为空")String permissionId) {

        iRoleService.bindRolePermission(roleId, Utils.parseIntegersList(Utils.splitStringToList(permissionId)));
        return ApiResultUtil.success(iRoleService.queryRoleInfo(roleId));
    }


    @ApiOperation(value = "通过角色ID查询选中的菜单")
    @ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = true, dataType = "Int")
    @PostMapping("/queryCheckedMenuByRoleId")
    public List<LayuiTreeBean> queryCheckedMenuByRoleId(@NotNull Integer id) {
        return iRoleService.queryCheckedMenusByRoleId(id);
    }

    @ApiOperation(value = "通过角色ID查询所有菜单")
    @ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = true, dataType = "Int")
    @PostMapping("/queryAllMenuByRoleId")
    public List<LayuiTreeBean> queryAllMenuByRoleId(@NotNull Integer id) {
        return iRoleService.queryMenusByRoleId(id);
    }

    @ApiOperation(value = "通过角色ID查询选中权限")
    @ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = true, dataType = "Int")
    @PostMapping("/queryCheckPermissionByRoleId")
    public ResultBean queryCheckPermissionByRoleId(Integer id) {
        return ApiResultUtil.success(iRoleService.queryAllPermissionByRoleId(id));
    }

}
