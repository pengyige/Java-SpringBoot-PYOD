package top.yigege.test.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.model.SysMenu;
import top.yigege.model.SysRole;
import top.yigege.model.SysUser;
import top.yigege.service.ISysMenuService;
import top.yigege.util.JsonUtil;
import top.yigege.vo.LayuiTreeBean;

import javax.annotation.Resource;
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
public class MenuServiceTest {



    @Resource
    private ISysMenuService iMenuService;

    @Test
    public void queryUserMenuTest() {
        List<LayuiTreeBean> menuList = iMenuService.queryTreeMenu();
        log.info("response:{}", JsonUtil.toJson(menuList));
    }


}
