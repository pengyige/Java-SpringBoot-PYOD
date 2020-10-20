package top.yigege.test.role;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.service.IMenuService;
import top.yigege.service.IRoleService;
import top.yigege.util.JsonUtil;
import top.yigege.vo.LayuiTreeBean;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: UserServiceTest
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月28日 10:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PyodApplication.class)
public class RoleServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceTest.class);


    @Resource
    private IRoleService iRoleService;

    @Test
    public void queryUserMenuTest() {
        PageBean menuList = iRoleService.queryRoleList(1,10,new HashMap<>());
        LOGGER.info("response:{}", JsonUtil.toJson(menuList));
    }


}
