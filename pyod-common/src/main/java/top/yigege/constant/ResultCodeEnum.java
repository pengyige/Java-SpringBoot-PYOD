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

    /**
     * shiro session失效
     */
    SESSION_EXPIRE(502,"登入时间过长,请重新登入"),


    //1000-9999 业务错误
    /**
     * 非法业务
     */
    ILLEGAL_BUSINESS(1000,"非法业务"),

    /**
     * 调用微信API出错
     */
    CALL_WEIXIN_API_ERROR(1001,"调用微信API出错"),

    /**
     * 缓存session_key失效
     */
    REDIS_SESSION_KEY_EXPIRE(1002,"session_key已失效，请重新获取code"),

    /**
     * 用户不存在
     */
    NO_USER(1003,"用户不存在"),

    /**
     * 商品不存在
     */
    NO_PRODUCT(1004,"商品不存在"),

    /**
     * 下单失败
     */
    ORDERS_FAIL(1005,"下单失败"),

    /**
     * 主卡已存在
     */
    PRIMARY_CARD_EXIST(1006,"参数错误,主卡已存在"),

    /**
     * 卡片不存在
     */
    NO_CARD (1007,"卡片不存在"),

    /**
     * 卡片已被使用
     */
    CARD_UESD (1007,"此VIP卡已被绑定"),

    /**
     * 用户优惠券不存在
     */
    NO_USER_COUPON (1008,"用户优惠券不存在"),

    /**
     * 赠送失败，只有可使用的优惠券才能赠送
     */
    GIVE_FAIL(1009,"赠送失败，只有可使用且有效的优惠券才能赠送"),

    GET_FAIL(1010,"领取失败，只有赠送中且有效的优惠券才能领取"),

    NOT_FOUND_CDKEY(1011,"未找到对应CDKEY"),

    CDKEY_INVALID(1012,"CDKEY无效"),

    ALREADY_CDKEY_USED(1013,"CDKEY已被兑换过了"),


    VIP_ID_NOT_EXIST(1014,"购买充值返券商品时vip卡片ID必填"),

    VIP_CARD_BIRTHDAY_MODIFY_LIMIT(1015,"会员卡生日只能修改一次"),

    ACTIVITY_EXIST(1016,"该类型活动已存在，请先暂停后再操作"),

    USER_BIRTHDAY_MODIFY_LIMIT(1017,"用户生日只能修改一次"),

    QRCODE_INVALID(1018,"二维码无效"),

    CHARGE_FAIL(1019,"核销失败"),

    NO_COUPON_ACTIVITY(1020,"活动还未开始");


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
