package top.yigege.constant;

/**
 * @ClassName: BusinessFlagEnum
 * @Description:业务标识
 * @author: yigege
 * @date: 2020年09月28日 17:26
 */
public enum BusinessFlagEnum {

    USER(0, "U"),


    ROLE(1, "R"),

    CDKEY(3,"CDKEY"),

    COUPON_CHARGE_OFF(4,"CCO"),

    GATHERING(5,"G"),
    PRODUCT(2, "P");



    //VIP_CARD(3, "C");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    BusinessFlagEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
