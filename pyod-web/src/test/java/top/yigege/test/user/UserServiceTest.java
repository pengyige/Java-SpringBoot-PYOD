package top.yigege.test.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.SysMenu;
import top.yigege.model.SysRole;
import top.yigege.model.SysUser;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.ISysRoleService;
import top.yigege.service.ISysUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.JsonUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserServiceTest
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月28日 10:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PyodApplication.class)
@Slf4j
public class UserServiceTest {



    @Resource
    private ISysUserService userService;

    @Resource
    private ISysRoleService roleService;

    @Resource
    private IGenerateIDService iGenerateIDService;


    public void queryUserRolesMethodTest() {
            final String userNo = "79ebafa2-60f8-4ac4-99f9-34e5fb929b3d";

            SysUser user = userService.queryUserRoles(userNo);

            log.info("result:{}", JsonUtil.toJson(user));
    }

    public void generateIDTest() {
        for (int i = 0 ; i < 10; i++){
            log.info(i+":"+iGenerateIDService.getNo(BusinessFlagEnum.USER.getMsg()));
        }
    }


    public void bindUserRoleTest() {
        Integer userId = 10;

        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(1);
        roleIds.add(2);
        roleIds.add(3);

        userService.bindUserRoles(userId,roleIds);
    }


    public void bindRolePermissionTest() {
        Integer roleId = 10;

        List<Integer> permissionIds = new ArrayList<>();
        permissionIds.add(1);
        permissionIds.add(2);
        permissionIds.add(3);

        roleService.bindRolePermission(roleId,permissionIds);
    }


    public void queryRoleInfoTest() {
        SysRole role = roleService.queryRoleInfo(1);
        log.info("response:{}", JsonUtil.toJson(ApiResultUtil.success(role)));
    }

    public void queryUserListByPageTest() {
        PageBean userPage = userService.queryUserList(1,10,null);
        log.info("response:{}", JsonUtil.toJson(ApiResultUtil.success(userPage)));
    }

    @Test
    public void queryUserMenuTest() {
        List<SysMenu> menuList = userService.queryMenusByRoleNo("admin");
        log.info("response:{}", JsonUtil.toJson(ApiResultUtil.success(menuList)));
    }

}
