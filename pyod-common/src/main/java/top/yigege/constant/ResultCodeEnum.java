package top.yigege.constant;

/**
 * @ClassName: ResultCodeEnum
 * @Description:响应码
 * @author: yigege
 * @date: 2020年09月17日 16:52
 */
public enum ResultCodeEnum {

    //100-199 参数错误
    /**
     * 非法参数
     */
    ILLEGAL_PARAM(100,"非法参数"),

    /**
     * 签名不对
     */
    SIGN_ERROR(101,"缺少签名参数"),

    /**
     * 非法签名
     */
    ILLEGAL_SIGN_ERROR(102,"非法签名"),

    /**
     * token不能为空
     */
    TOKEN_ERROR(110,"token不能为空"),

    /**
     * 非法token
     */
    ILLEGAL_TOKEN_ERROR(111,"非法token或token已过期，请重新登录"),


    /**
     * 成功
     */
    SUCCESS(200, "操作成功!"),

    /**
     * 非法参数
     */
    NOT_FOUND(404,"资源不存在"),


    //500-999 系统错误
    /**
     * 失败
     */
    ERROR(500, "未知错误!"),

    /**
     * 权限不足
     */
    NO_PERMISSION(501,"权限不足"),


    //1000-9999 业务错误
    /**
     * 非法业务
     */
    ILLEGAL_BUSINESS(1000,"非法业务"),

    /**
     * 调用微信API出错
     */
    CALL_WEIXIN_API_ERROR(1001,""),

    /**
     * 缓存session_key失效
     */
    REDIS_SESSION_KEY_EXPIRE(1002,"session_key已失效，请重新获取code"),

    /**
     * 用户不存在
     */
    NO_USER(1003,"用户不存在");


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
