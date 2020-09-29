package top.yigege.exception;

import top.yigege.constant.ResultCodeEnum;
import top.yigege.vo.ResultBean;

/**
 * @ClassName: CommonException
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月17日 17:31
 */
public abstract class AbstractCommonException extends RuntimeException implements IPyodException{

    protected int code;

    protected String message;


    @Override
    public ResultBean getResultBean() {
        return new ResultBean(code,null, message);
    }
}
