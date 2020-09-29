package top.yigege.test.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.Role;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IRoleService;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.JsonUtil;

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
public class UserServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);


    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IGenerateIDService iGenerateIDService;


    public void queryUserRolesMethodTest() {
            final String userNo = "79ebafa2-60f8-4ac4-99f9-34e5fb929b3d";

            User user = userService.queryUserRoles(userNo);

            LOGGER.info("result:{}", JsonUtil.toJson(user));
    }

    public void generateIDTest() {
        for (int i = 0 ; i < 10; i++){
            LOGGER.info(i+":"+iGenerateIDService.getNo(BusinessFlagEnum.USER.getMsg()));
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

    @Test
    public void bindRolePermissionTest() {
        Integer roleId = 10;

        List<Integer> permissionIds = new ArrayList<>();
        permissionIds.add(1);
        permissionIds.add(2);
        permissionIds.add(3);

        roleService.bindRolePermission(roleId,permissionIds);
    }

    @Test
    public void queryRoleInfoTest() {

        Role role = roleService.queryRoleInfo(1);
        LOGGER.info("response:{}", JsonUtil.toJson(ApiResultUtil.success(role)));
    }

}
