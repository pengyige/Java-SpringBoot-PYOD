package top.yigege.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: LayuiTableResultBean
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月16日 15:25
 */
@ApiModel("Layui表格响应实体")
public class LayuiTableResultBean {


    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "提示")
    private String msg;

    @ApiModelProperty(value = "总条数")
    private int count;

    @ApiModelProperty(value = "数据")
    private List data;



    public LayuiTableResultBean(int code, String msg, int count, List data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
