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
     * 金币清空持续时间
     */
    COIN_CLEAR_DURATION_TIME("COIN_CLEAR_DURATION_TIME");


    String code;

    DictCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
