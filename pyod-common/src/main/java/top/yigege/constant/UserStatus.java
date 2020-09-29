package top.yigege.constant;

/**
 * @ClassName: UserStatus
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月24日 13:56
 */
public enum UserStatus {

    NORMAL(1,"正常"),

    EXCEPTION(2,"异常");

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    UserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
