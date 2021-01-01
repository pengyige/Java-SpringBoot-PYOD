package top.yigege.test.role;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.service.ISysRoleService;
import top.yigege.util.JsonUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassName: UserServiceTest
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月28日 10:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PyodApplication.class)
@Slf4j
public class RoleServiceTest {



    @Resource
    private ISysRoleService iRoleService;

    @Test
    public void queryUserMenuTest() {
        PageBean menuList = iRoleService.queryRoleList(1,10,new HashMap<>());
        log.info("response:{}", JsonUtil.toJson(menuList));
    }


}
