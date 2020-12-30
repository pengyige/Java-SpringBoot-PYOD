package top.yigege.constant;

/**
 * @ClassName: EnvType
 * @Description:环境类型
 * @author: yigege
 * @date: 2020年12月13日 16:43
 */
public enum EnvType {

    PROD("prod", "生产环境"),

    DEV("dev", "开发环境"),

    TEST("test", "测试环境");


    private String code;

    private String desc;

    EnvType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    ;

    public String getCode() {
        return code;
    }
}