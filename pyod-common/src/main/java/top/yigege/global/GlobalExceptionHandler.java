package top.yigege.global;

import org.apache.shiro.authc.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.yigege.exception.BusinessException;
import top.yigege.exception.NoPermissionException;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:全局异常处理
 * @author: yigege
 * @date: 2020年09月17日 17:27
 */
@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理 包含所有500状态的错误。404单独在ErrorController处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultBean exceptionHandler(Exception exception) {
        if (exception instanceof MissingServletRequestParameterException) {
            return ApiResultUtil.paramError(exception.getMessage());
        }else if (exception instanceof AccountException){
            //shiro 认证失败
            return ApiResultUtil.paramError(exception.getMessage());
        }
        LOGGER.info("exceptionHandler");
        LOGGER.error(exception.getMessage(), exception);
        return ApiResultUtil.fail();
    }

    /**
     * 权限不足异常处理
     *
     * @param noPermissionException
     * @return
     */
    @ExceptionHandler(NoPermissionException.class)
    public ResultBean noPermissionExceptionHandler(NoPermissionException noPermissionException) {
        LOGGER.info("noPermissionExceptionHandler");
        LOGGER.error(noPermissionException.getMessage(), noPermissionException);
        return noPermissionException.getResultBean();
    }

    /**
     * 业务异常处理
     *
     * @param businessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResultBean commonExceptionHandler(BusinessException businessException) {
        LOGGER.info("commonExceptionHandler");
        LOGGER.error(businessException.getMessage(), businessException);

        return businessException.getResultBean();
    }


    /**
     * 表单参数校验异常
     *
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResultBean paramValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        LOGGER.info("paramValidExceptionHandler");

        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();

        StringBuffer buffer = new StringBuffer();
        for (ObjectError error : errors) {
            buffer.append(error.getDefaultMessage()).append(";");
        }
        return ApiResultUtil.paramError(buffer.toString());
    }

    /**
     * json提交参数校验异常
     * @param constraintViolationException
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultBean constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException) {
        LOGGER.info("constraintViolationExceptionHandler");
        Set<ConstraintViolation<?>> cves = constraintViolationException.getConstraintViolations();
        StringBuffer errorMsg = new StringBuffer();

        for (ConstraintViolation cv : cves) {
            errorMsg.append(cv.getMessageTemplate()).append(";");
        }

        return ApiResultUtil.paramError(errorMsg.toString());
    }



}
