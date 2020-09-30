package top.yigege.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * @ClassName: PageUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月30日 11:44
 */
public class PageUtil {


    private PageUtil() {

    }


    /**
     *
     * @param page
     * @param data
     * @return
     */
    public static PageBean getPageBean(Page page, List data) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage((int)page.getCurrent());
        pageBean.setPageSize((int)page.getSize());
        pageBean.setTotalCount((int)page.getTotal());
        pageBean.setTotalPage((int)page.getPages());
        pageBean.setHasMore(page.hasNext());
        pageBean.setData(data);
        return pageBean;
    }
}
