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
import top.yigege.annotation.WebLog;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.Role;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IRoleService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.UUID;

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
@RequestMapping("/role")
@Validated
public class RoleController {

    @Resource
    IGenerateIDService iGenerateIDService;

    @Resource
    IRoleService iRoleService;


    @ApiOperation(value = "添加角色", notes = "添加一个新的角色", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "角色名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = true, dataType = "String"),
    })
    @PostMapping("/addRole")
    public ResultBean addRole(@Valid @ApiIgnore Role role) {
        iGenerateIDService.getNo(BusinessFlagEnum.ROLE.getMsg());
        iRoleService.insert(role);
        return ApiResultUtil.success(role);
    }
}
