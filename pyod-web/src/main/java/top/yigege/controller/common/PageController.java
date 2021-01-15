package top.yigege.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName: PageController
 * @Description:页面跳转控制
 * @author: yigege
 * @date: 2020年09月25日 17:55
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("/")
    public String index()  {
        return "forward:index.html";
    }

    @GetMapping("/web")
    public String web()  {
        return "forward:index.html";
    }

    @GetMapping("/api/doc")
    public String webDoc() {
        return "redirect:/doc.html";
    }
}
