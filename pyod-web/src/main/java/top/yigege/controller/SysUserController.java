package top.yigege.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.yigege.annotation.WebLog;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.ResultCodeEnum;

import top.yigege.dto.modules.sysUser.AddUserDTO;
import top.yigege.dto.modules.sysUser.ModifyUserDTO;
import top.yigege.dto.modules.sysUser.QueryUserInfoResDTO;
import top.yigege.dto.modules.sysUser.QueryUserPageListDTO;
import top.yigege.model.SysMenu;
import top.yigege.model.SysRole;
import top.yigege.model.SysUser;
import top.yigege.service.ISysUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-23
 */

@Api(tags = "用户相关接口", description = "提供用户相关API")
@RestController
@RequestMapping("/web/sysUser")
@Slf4j
@Validated
public class SysUserController {



    @Autowired
    ISysUserService iUserService;

    @PostMapping("/addUser")
    public ResultBean addUser(@Valid AddUserDTO addUserDTO) {
        return ApiResultUtil.success(iUserService.addUser(addUserDTO));
    }


    @PostMapping("/updateUser")
    public ResultBean updateUser(@Valid ModifyUserDTO modifyUserDTO) {
        SysUser modifyUser = new SysUser();
        BeanUtil.copyProperties(modifyUser, modifyUserDTO);

        iUserService.updateById(modifyUser);
        SysUser dbUser = new SysUser();
        if (StringUtils.isNotBlank(modifyUserDTO.getRoleIds())) {
            iUserService.bindUserRoles(modifyUser.getUserId(),
                    Utils.parseIntegersList(Utils.splitStringToList(modifyUserDTO.getRoleIds())));
            dbUser = iUserService.queryUserRolesById(modifyUser.getUserId());
        }

        return ApiResultUtil.success(dbUser);
    }


    @PostMapping("/queryUserList")
    public LayuiTableResultBean queryUserList(QueryUserPageListDTO queryUserPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iUserService.queryUserList(queryUserPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }


    @PostMapping(value = "/loadUserDetail")
    public ResultBean<SysUser> loadUserDetail(@NotNull(message = "用户id不能为空") Integer userId) {
        return ApiResultUtil.success(iUserService.queryUserRolesById(userId));
    }

    @ApiOperation(value = "通过昵称获取用户详情信息", notes = "通过昵称获取用户详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "用户昵称，多个用逗号分割", required = true, dataType = "string")
    })
    @WebLog
    @PostMapping("/loadUserByNickname}")
    public ResultBean loadUserByNickname(@NotBlank(message = "昵称不能为空") String nickname) {
        return ApiResultUtil.success(iUserService.queryUserByNickname(Utils.splitStringToList(nickname)));

    }

    @ApiOperation(value = "绑定角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色ID,多个用逗号分隔", required = true, dataType = "Int")
    })
    @PostMapping("/bindRole}")
    public ResultBean bindRole(@NotNull(message = "用户ID不能为空") Integer userId,
                               @NotNull(message = "角色ID不能为空") String roleId) {

        iUserService.bindUserRoles(userId, Utils.parseIntegersList(Utils.splitStringToList(roleId)));
        return ApiResultUtil.success();
    }


    @ApiOperation(value = "用户登入")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userNo", value = "用户编号", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "psw", value = "密码", required = true, dataType = "string")
    })
    @PostMapping("/login")
    public ResultBean login(@NotNull(message = "用户编号不能为空") String userNo,
                            @NotNull(message = "用户密码不能为空") String psw) {
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(userNo, psw);
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        // 开始认证，这一步会跳到我们自定义的 Realm 中
        subject.login(token);

        return ApiResultUtil.success(SessionUtil.getUser());
    }

    @PostMapping("/queryUserInfo")
    public ResultBean queryUserInfo() {
        QueryUserInfoResDTO queryUserInfoResDTO = new QueryUserInfoResDTO();
        queryUserInfoResDTO.setUser(SessionUtil.getUser());
        queryUserInfoResDTO.setCheckedRole(SessionUtil.getCurrentUserRole());
        return ApiResultUtil.success(queryUserInfoResDTO);
    }

    @ApiOperation(value = "设置当前用户选择的角色")
    @PostMapping("/setCurrentUserRole")
    public ResultBean setCurrentUserRole(@NotNull(message = "角色ID不能为空") Integer roleId) {

        List<SysRole> roleList = SessionUtil.getUser().getRoleList();
        for (SysRole role : roleList) {
            if (role.getRoleId().equals(roleId)) {
                SessionUtil.setCurrentUserRole(role);
                break;
            }
        }

        return ApiResultUtil.success();
    }



    @PostMapping("/queryUserMenuByRole")
    public ResultBean queryUserMenuByRole() {
        if (null == SessionUtil.getCurrentUserRole()) {
            return logout();
        }
        List<SysMenu> menuList = iUserService.queryMenusByRoleNo(SessionUtil.getCurrentUserRole().getRoleNo());
        return ApiResultUtil.success(menuList);
    }

    @PostMapping("/deleteUserById")
    public ResultBean deleteUserById(@NotNull(message = "用户ID不能为空") Integer id) {
        iUserService.deleteUserById(id);
        return ApiResultUtil.success();
    }


    @ApiOperation(value = "退出系统")
    @PostMapping("/logout")
    public ResultBean logout() {
        //1. 得到当前主体
        Subject subject = SecurityUtils.getSubject();

        //2. logout
        subject.logout();

        //3. 清除Session
        SessionUtil.removeAllSession();

        return ApiResultUtil.success();
    }

}
