package top.yigege.test.user;

import com.baomidou.mybatisplus.core.metadata.IPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yigege.PyodApplication;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.Menu;
import top.yigege.model.Role;
import top.yigege.model.User;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IMenuService;
import top.yigege.service.IRoleService;
import top.yigege.service.IUserService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.JsonUtil;
import top.yigege.vo.LayuiTreeBean;
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
public class MenuServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceTest.class);


    @Resource
    private IMenuService iMenuService;

    @Test
    public void queryUserMenuTest() {
        List<LayuiTreeBean> menuList = iMenuService.queryTreeMenu();
        LOGGER.info("response:{}", JsonUtil.toJson(menuList));
    }


}
