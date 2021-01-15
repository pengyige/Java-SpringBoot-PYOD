package top.yigege.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: PageBean
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月30日 11:42
 */
@ApiModel("分页实体")
public class PageBean {

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private int currentPage = 1;

    /**
     * 页面大小
     */
    @ApiModelProperty("页面大小")
    private int pageSize = 10;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int totalPage;

    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    private int totalCount;

    /**
     * 是否有下一页
     */
    @ApiModelProperty("是否有下一页")
    private boolean hasMore;

    /**
     * 分页数据
     */
    @ApiModelProperty("分页数据")
    private List data;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
