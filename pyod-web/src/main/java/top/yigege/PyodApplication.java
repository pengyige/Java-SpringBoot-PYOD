package top.yigege;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @ClassName: PyodApplication
 * @Description:PyodApplication Main
 * @author: yigege
 * @date: 2020年09月17日 11:18
 */
@SpringBootApplication
@MapperScan("top.yigege.dao.**")
public class PyodApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PyodApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(PyodApplication.class, args);
        LOG.info("========================pyod start success!========================");
    }
}
