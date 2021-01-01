package top.yigege;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @ClassName: PyodApplication
 * @Description:PyodApplication Main
 * @author: yigege
 * @date: 2020年09月17日 11:18
 */
@SpringBootApplication
@MapperScan("top.yigege.dao.**")
@Slf4j
public class PyodApplication {




    public static void main(String[] args) {
        SpringApplication.run(PyodApplication.class, args);
        log.info("========================pyod start success!========================");
    }

}
