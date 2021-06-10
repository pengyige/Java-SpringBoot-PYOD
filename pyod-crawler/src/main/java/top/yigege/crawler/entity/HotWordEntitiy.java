package top.yigege.crawler.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: HotWordEntitiy
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月20日 17:08
 */
@Data
public class HotWordEntitiy {

    Long hotWordId;

    String content;

    Date createTime;

    Date updateTime;
}
