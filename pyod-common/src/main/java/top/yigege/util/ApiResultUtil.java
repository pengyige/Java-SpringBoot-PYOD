package top.yigege.util;

import top.yigege.constant.ResultCodeEnum;
import top.yigege.vo.ResultBean;

/**
 * @ClassName: ApiResultUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月18日 9:44
 */
public class ApiResultUtil {

    private ApiResultUtil() {

    }

    /**
     * 成功
     * @return
     */
    public static ResultBean success() {
        return new ResultBean(ResultCodeEnum.SUCCESS.getCode(),null,ResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBean success(Object data) {
        return new ResultBean(ResultCodeEnum.SUCCESS.getCode(),data,ResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 失败
     * @return
     */
    public static ResultBean fail() {
        return new ResultBean(ResultCodeEnum.ERROR.getCode(),null,ResultCodeEnum.ERROR.getMsg());
    }

    /**
     * 资源未找到
     * @return
     */
    public static ResultBean notFound() {
        return new ResultBean(ResultCodeEnum.NOT_FOUND.getCode(),null,ResultCodeEnum.NOT_FOUND.getMsg());
    }

    /**
     * 非法参数
     * @return
     */
    public static ResultBean paramError() {
        return new ResultBean(ResultCodeEnum.ILLEGAL_PARAM.getCode(),null,ResultCodeEnum.ILLEGAL_PARAM.getMsg());
    }

    /**
     * 非法参数
     * @param msg
     * @return
     */
    public static ResultBean paramError(String msg) {
        return new ResultBean(ResultCodeEnum.ILLEGAL_PARAM.getCode(),null,msg);
    }


    /**
     * 自定义返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultBean custom(int code, String msg, Object data) {
        return new ResultBean(code, data, msg);
    }

    /**
     * 自定义返回
     * @param resultCodeEnum
     * @return
     */
    public static ResultBean custom(ResultCodeEnum resultCodeEnum) {
        return new ResultBean(resultCodeEnum);
    }
}
