package top.yigege.crawler.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.yigege.crawler.Pipeline.HotWordPipeline;
import top.yigege.crawler.constants.CrawlerConstants;
import top.yigege.crawler.processor.HotWordPageProcessor;
import us.codecraft.webmagic.Spider;

/**
 * @ClassName: HotWordTask
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月20日 17:44
 */
@Component
@Slf4j
public class HotWordTask {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void run() {
        Spider.create(new HotWordPageProcessor())
                .addUrl(CrawlerConstants.TOU_TIAO_NEW_URL)
                //自定义Pipeline，保存json文件到本地
                .addPipeline(new HotWordPipeline("D:\\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
