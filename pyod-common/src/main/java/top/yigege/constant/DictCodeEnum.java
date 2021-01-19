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
     * 消费金额获取金币
     */
    SPEND_MONEY_GET_COIN("SPEND_MONEY_GET_COIN"),

    /**
     * 赠送优惠券未领取时间 单位秒
     */
    GIVE_USER_COUPON_RETURN_TIME("GIVE_USER_COUPON_RETURN_TIME"),

    /**
     * 金币清空持续时间 单位秒
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
