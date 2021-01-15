package top.yigege.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: LayuiFileUploadResultBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 20:07
 */
@Data
@ApiModel("Layui文件上传响应实体")
public class LayuiFileUploadResultBean {

    @ApiModelProperty("响应码")
    private int code;

    @ApiModelProperty("提示")
    private String msg;

    @ApiModelProperty("数据")
    private Map<String,String> data;


}


