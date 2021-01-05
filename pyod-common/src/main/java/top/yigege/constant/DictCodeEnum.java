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
    PROJECT_NAME("PROJECT_NAME");




    String code;

    DictCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
