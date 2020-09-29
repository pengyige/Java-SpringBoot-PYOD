package top.yigege.constant;

/**
 * @ClassName: ResultCodeEnum
 * @Description:响应码
 * @author: yigege
 * @date: 2020年09月17日 16:52
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功!"),


    /**
     * 非法参数
     */
    ILLEGAL_PARAM(300,"非法参数"),

    /**
     * 非法参数
     */
    NOT_FOUND(404,"资源不存在"),

    /**
     * 失败
     */
    ERROR(-1, "未知错误!"),

    /**
     * 权限不足
     */
    NO_PERMISSION(-2,"权限不足"),

    /**
     * 非法业务
     */
    ILLEGAL_BUSINESS(-3,"非法业务");


    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }



    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
