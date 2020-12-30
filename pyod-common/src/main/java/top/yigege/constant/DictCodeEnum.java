package top.yigege.constant;

/**
 * @ClassName: DictCodeEnum
 * @Description:字典code
 * @author: yigege
 * @date: 2020年11月02日 14:55
 */
public enum DictCodeEnum {

    /**
     * 项目名
     */
    PROJECT_NAME("PROJECT_NAME"),

    /**
     * 微信APPID
     */
    WX_APPID("WX_APPID"),

    /**
     * 微信密钥
     */
    WX_SECRET("WX_SECRET");



    String code;

    DictCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
