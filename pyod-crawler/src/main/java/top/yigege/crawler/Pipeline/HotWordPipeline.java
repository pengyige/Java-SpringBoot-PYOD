package top.yigege.crawler.Pipeline;

import com.alibaba.fastjson.JSON;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: HotWordPipeline
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月20日 17:39
 */
public class HotWordPipeline extends FilePersistentBase implements Pipeline {

    public HotWordPipeline(String path) {
        this.setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String path = "HotWordPipeline";

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.getFile(path+ ".json")));
            printWriter.write(JSON.toJSONString(resultItems.get("data")));
            printWriter.close();
        } catch (IOException var5) {

        }
    }
}
