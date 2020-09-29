package top.yigege.constant;

/**
 * @ClassName: SexType
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月23日 17:58
 */
public enum SexType {

    NONE(-1,"不详"),

    MAN(0,"男"),

    WOMAN(1,"女");

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    SexType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
