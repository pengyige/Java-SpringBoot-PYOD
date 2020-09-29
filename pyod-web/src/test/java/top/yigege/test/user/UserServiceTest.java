package top.yigege.test.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IUserService;
import top.yigege.util.JsonUtil;

import javax.annotation.Resource;

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
    private IGenerateIDService iGenerateIDService;


    public void queryUserRolesMethodTest() {
            final String userNo = "79ebafa2-60f8-4ac4-99f9-34e5fb929b3d";

            User user = userService.queryUserRoles(userNo);

            LOGGER.info("result:{}", JsonUtil.toJson(user));
    }

    @Test
    public void generateIDTest() {

        for (int i = 0 ; i < 10; i++){
            LOGGER.info(i+":"+iGenerateIDService.getNo(BusinessFlagEnum.USER.getMsg()));
        }
    }

}
