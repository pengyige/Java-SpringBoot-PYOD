package top.yigege.constant;

/**
 * @ClassName: PerimissionType
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月21日 17:03
 */
public enum PermissionType {


    QUERY("QUERY","查询权限"),

    MODIFY("MODIFY","修改权限"),

    ADD("ADD","添加权限"),

    DELETE("DELETE", "删除权限");

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    PermissionType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
