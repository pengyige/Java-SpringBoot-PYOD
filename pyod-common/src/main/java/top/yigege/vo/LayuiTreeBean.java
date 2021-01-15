package top.yigege.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LayuiTreeBean
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月19日 15:46
 */
@ApiModel("Layui菜单树实体")
public class LayuiTreeBean implements Comparable<LayuiTreeBean>{

    @ApiModelProperty("id")
    private int id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("是否展开")
    private boolean spread = false;

    @ApiModelProperty("是否选中")
    private boolean checked = false;

    @ApiModelProperty("顺序")
    private int sort;

    @ApiModelProperty("父id")
    private int pid;

    @ApiModelProperty("子菜单")
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int compareTo(LayuiTreeBean menu) {
        return this.sort - menu.sort;
    }


    @Override
    public boolean equals(Object obj) {
        return this.id == ((LayuiTreeBean)obj).id;
    }
}
