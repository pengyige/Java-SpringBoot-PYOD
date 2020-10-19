package top.yigege.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LayuiTreeBean
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月19日 15:46
 */
public class LayuiTreeBean implements Comparable<LayuiTreeBean>{

    private int id;

    private String title;

    private boolean spread = false;


    private int sort;

    private int pid;

    private List<LayuiTreeBean> children = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<LayuiTreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTreeBean> children) {
        this.children = children;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public int compareTo(LayuiTreeBean menu) {
        return this.sort - menu.sort;
    }
}
