package top.yigege.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.yigege.annotation.WebLog;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.global.GlobalExceptionHandler;
import top.yigege.model.Menu;
import top.yigege.model.Role;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Transformer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
@RequestMapping("/user")
@Validated
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService iUserService;

    @ApiOperation(value = "添加用户", notes = "添加一个新的用户", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "用户昵称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "用户性别", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "tel", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "roleIds", value = "角色ID，多个ID用逗号隔开", required = false, dataType = "String")

    })
    @WebLog
    @RequestMapping("/addUser")
    public ResultBean addUser(@Valid @ApiIgnore User user, String roleIds) {
        return ApiResultUtil.success(iUserService.addUser(user, Utils.parseIntegersList(Utils.splitStringToList(roleIds))));
    }


    @ApiOperation(value = "更新用户",  response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "昵称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "性别", required = false, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "tel", value = "联系方式", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "roleIds", value = "角色ID，多个ID用逗号隔开", required = false, dataType = "String")

    })
    @RequestMapping("/updateUser")
    public ResultBean updateUser(
            @NotNull(message = "用户ID不能为空") Integer userId,
            String nickname,
            Integer sex,
            String tel,
            String password,
            String remark,
            String roleIds) {
        User dbUser = iUserService.getById(userId);
        if (StringUtils.isNotBlank(nickname)){
            dbUser.setNickname(nickname);
        }

        if (null != sex) {
            dbUser.setSex(sex);
        }

        if (StringUtils.isNotBlank(tel)) {
            dbUser.setTel(tel);
        }

        if (StringUtils.isNotBlank(password)) {
            dbUser.setPassword(DigestUtil.md5Hex(password));
        }

        if (StringUtils.isNotEmpty(remark)) {
            dbUser.setRemark(remark);
        }

        iUserService.updateById(dbUser);
        if (StringUtils.isNotBlank(roleIds)) {
            iUserService.bindUserRoles(userId, Utils.parseIntegersList(Utils.splitStringToList(roleIds)));
            dbUser = iUserService.queryUserRoles(dbUser.getNo());
        }

        return ApiResultUtil.success(dbUser);
    }


    @ApiOperation("查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = false, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = false, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "昵称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "no", value = "编号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "性别", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "tel", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "状态", required = false, dataType = "String"),

    })
    @PostMapping("/queryUserList")
    public LayuiTableResultBean queryUserList(int page,
                                              @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer userId,
                                              @RequestParam(required = false) String nickname,
                                              @RequestParam(required = false) String no,
                                              @RequestParam(required = false, defaultValue = PyodConstant.Common.ALL + "") Integer sex,
                                              @RequestParam(required = false) String tel,
                                              @RequestParam(required = false, defaultValue = PyodConstant.Common.ALL + "") Integer status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("nickname", nickname);
        paramMap.put("no", no);
        paramMap.put("sex", sex);
        paramMap.put("tel", tel);
        paramMap.put("status", status);

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iUserService.queryUserList(page, pageSize, paramMap);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
            LOGGER.error(e.getMessage(), e);
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }


    @ApiOperation(value = "获取用户详情信息", notes = "获取用户详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "int")
    })
    @WebLog
    @PostMapping()
    @RequestMapping(value = "/loadUserDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultBean<User> loadUserDetail(@RequestParam("id") @ApiIgnore Integer id) {
        return ApiResultUtil.success(iUserService.queryUserRolesById(id));
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

    @ApiOperation(value = "通过获取当前用户信息")
    @PostMapping("/queryUserInfo")
    public ResultBean queryUserInfo() {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("user",SessionUtil.getUser());
        returnMap.put("checkedRole",SessionUtil.getCurrentUserRole());
        return ApiResultUtil.success(returnMap);
    }

    @ApiOperation(value = "设置当前用户选择的角色")
    @PostMapping("/setCurrentUserRole")
    public ResultBean setCurrentUserRole(@NotNull(message = "角色ID不能为空") Integer roleId) {

        List<Role> roleList = SessionUtil.getUser().getRoleList();
        for (Role role : roleList) {
            if (role.getRoleId().equals(roleId)) {
                SessionUtil.setCurrentUserRole(role);
                break;
            }
        }

        return ApiResultUtil.success();
    }


    @ApiOperation(value = "通过用户角色获取菜单")
    @PostMapping("/queryUserMenuByRole")
    public ResultBean queryUserMenuByRole() {
        List<Menu> menuList = iUserService.queryMenusByRoleNo(SessionUtil.getCurrentUserRole().getRoleNo());
        return ApiResultUtil.success(menuList);
    }


    @ApiOperation(value = "通过ID删除用户")
    @ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "Int")
    @PostMapping("/deleteUserById")
    public ResultBean deleteUserById(@NotNull(message = "用户ID不能为空") Integer id) {
        iUserService.deleteUserById(id);
        return ApiResultUtil.success();
    }

}
