package top.yigege.vo;

import io.swagger.annotations.ApiModelProperty;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.util.ApiResultUtil;

/**
 * @ClassName: ResultBean
 * @Description:公共返回bean
 * @author: yigege
 * @date: 2020年09月17日 16:40
 */
public class ResultBean<T> {

    private Integer code = ResultCodeEnum.SUCCESS.getCode();

    private T data;

    private String message;

    public ResultBean(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResultBean(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
