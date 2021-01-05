package top.yigege.controller;


import cn.hutool.core.bean.BeanUtil;
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

import top.yigege.dto.modules.sysRole.AddRoleDTO;
import top.yigege.dto.modules.sysRole.ModifyRoleDTO;
import top.yigege.dto.modules.sysRole.QueryRolePageListDTO;
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



    @PostMapping("/addRole")
    public ResultBean addRole(@Valid AddRoleDTO addRoleDTO) {
        SysRole dbRole = iRoleService.addRole(addRoleDTO);
        return ApiResultUtil.success(dbRole);
    }

    @PostMapping("/modifyRole")
    public ResultBean modifyRole(@Valid ModifyRoleDTO modifyRoleDTO) {
        SysRole dbRole = iRoleService.modifyRole(modifyRoleDTO);
        return ApiResultUtil.success(dbRole);
    }


    @PostMapping("/deleteRoleByIds")
    public ResultBean deleteRoleByIds(@NotBlank(message = "角色ID不能为空") String roleIds) {
        iRoleService.deleteRoleContainsRecord(Utils.parseIntegersList(Utils.splitStringToList(roleIds)));
        return ApiResultUtil.success();
    }


    @PostMapping("/queryRoleDetail")
    public ResultBean queryRoleDetail(@NotNull(message = "角色id不能为空")  Integer roleId) {
        return ApiResultUtil.success(iRoleService.queryRoleInfo(roleId));
    }


    @PostMapping("/queryRoleList")
    public LayuiTableResultBean queryRoleList(QueryRolePageListDTO queryRolePageListDTO) {
        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iRoleService.queryRoleList(queryRolePageListDTO);
        }catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
            log.error(e.getMessage(), e);
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    
    @PostMapping("/bindPermission}")
    public ResultBean bindPermission(@NotNull(message = "角色ID不能为空") Integer roleId,
                               @NotNull(message = "权限ID不能为空")String permissionId) {

        iRoleService.bindRolePermission(roleId, Utils.parseIntegersList(Utils.splitStringToList(permissionId)));
        return ApiResultUtil.success(iRoleService.queryRoleInfo(roleId));
    }


    @PostMapping("/queryCheckedMenuByRoleId")
    public List<LayuiTreeBean> queryCheckedMenuByRoleId(@NotNull(message = "角色id不能为空") Integer roleId) {
        return iRoleService.queryCheckedMenusByRoleId(roleId);
    }

    @PostMapping("/queryAllMenuByRoleId")
    public List<LayuiTreeBean> queryAllMenuByRoleId(@NotNull Integer roleId) {
        return iRoleService.queryMenusByRoleId(roleId);
    }

    @PostMapping("/queryCheckPermissionByRoleId")
    public ResultBean queryCheckPermissionByRoleId(Integer roleId) {
        return ApiResultUtil.success(iRoleService.queryAllPermissionByRoleId(roleId));
    }

}
