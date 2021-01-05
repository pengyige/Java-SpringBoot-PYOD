package top.yigege.vo;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName: LayuiFileUploadResultBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 20:07
 */
@Data
public class LayuiFileUploadResultBean {

    private int code;

    private String msg;

    private Map<String,String> data;


}


