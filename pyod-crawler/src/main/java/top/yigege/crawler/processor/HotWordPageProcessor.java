package top.yigege.crawler.processor;

import top.yigege.crawler.entity.HotWordEntitiy;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HotWordPageProcessor
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月20日 17:19
 */
public class HotWordPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(5).setUserAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");

    @Override
    public void process(Page page) {
        List<String> pageItemList = page.getHtml().xpath("//ul[@class=nav_navcon]/text()").all();
        ArrayList<HotWordEntitiy> list = new ArrayList<>();
        for (int i = 0; i < pageItemList.size(); i++) {
            Html html = Html.create(pageItemList.get(i));
            HotWordEntitiy hotWordEntitiy=new HotWordEntitiy();
            hotWordEntitiy.setContent(html.get());
            list.add(hotWordEntitiy);
        }

        page.putField("data", list);

        if (page.getResultItems().get("data") == null) {
            //skip this page
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
