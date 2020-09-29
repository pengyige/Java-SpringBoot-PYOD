package top.yigege.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import top.yigege.model.User;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Transformer;
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

    @Autowired
    IUserService iUserService;


    @ApiOperation(value = "添加用户", notes = "添加一个新的用户", response = ResultBean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "用户昵称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "用户性别", required = true, dataType = "Int"),
            @ApiImplicitParam(paramType = "query", name = "tel", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String")

    })
    @WebLog
    @PostMapping("/addUser")
    public ResultBean addUser(@Valid @ApiIgnore User user) {
        user.setNo(UUID.randomUUID().toString());
        iUserService.insert(user);
        return ApiResultUtil.success(user);
    }


    public ResultBean queryUserList(int page , int pageSize) {

        IPage<User> iPage = iUserService.queryUserList(page,pageSize);
        return ApiResultUtil.success(iPage);
    }


    @ApiOperation(value = "获取用户详情信息", notes = "获取用户详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "int")
    })
    @WebLog
    @PostMapping()
    @RequestMapping(value = "/loadUserDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultBean<User> loadUserDetail(@RequestParam("userid") @ApiIgnore Integer userid) {
        return ApiResultUtil.success(iUserService.selectById(userid));
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
                               @NotNull(message = "角色ID不能为空")String roleId) {

        iUserService.bindUserRoles(userId, Utils.parseIntegersList(Utils.splitStringToList(roleId)));
        return ApiResultUtil.success();
    }
}
