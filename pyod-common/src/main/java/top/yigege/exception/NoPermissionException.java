package top.yigege.exception;

import top.yigege.constant.ResultCodeEnum;

/**
 * @ClassName: NoPermissionException
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月17日 17:35
 */
public class NoPermissionException extends AbstractCommonException{

    public NoPermissionException() {
        this.code = ResultCodeEnum.NO_PERMISSION.getCode();
        this.message = ResultCodeEnum.NO_PERMISSION.getMsg();
    }

    public NoPermissionException(String msg) {
        this.code = ResultCodeEnum.NO_PERMISSION.getCode();
        this.message = msg;
    }
}
