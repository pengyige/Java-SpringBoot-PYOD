package top.yigege.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: QueryWeatherDataJob
 * @Description:查询天气数据,每分钟执行
 * @author: yigege
 * @date: 2021年01月08日 12:01
 */
@Component
@Slf4j
public class QueryWeatherDataJob {



    //@Scheduled(cron="0 0/1 * * * ?")
    public void run() throws Exception {
        log.info("QueryWeatherDataJob run() start");



        log.info("QueryWeatherDataJob run() end");
    }
}
