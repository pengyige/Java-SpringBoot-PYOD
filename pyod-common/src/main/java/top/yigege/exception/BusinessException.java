package top.yigege.exception;

import top.yigege.constant.ResultCodeEnum;

/**
 * @ClassName: BusinessException
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月18日 10:01
 */
public class BusinessException extends AbstractCommonException{

    public BusinessException() {
        super();
        this.code = ResultCodeEnum.ILLEGAL_BUSINESS.getCode();
        this.message = ResultCodeEnum.ILLEGAL_BUSINESS.getMsg();

    }

    public BusinessException(String msg) {
        super(msg);
        this.code = ResultCodeEnum.ILLEGAL_BUSINESS.getCode();
        this.message = msg;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMsg();
    }
}
