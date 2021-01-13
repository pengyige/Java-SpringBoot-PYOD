package top.yigege.global;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
@Slf4j
public class GlobalExceptionHandler {
    
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
        }else if (exception instanceof HttpRequestMethodNotSupportedException) {
            //请求方法不支持
            return ApiResultUtil.paramError(exception.getMessage());
        }
        log.info("exceptionHandler");
        log.error(exception.getMessage(), exception);
        return ApiResultUtil.fail();
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResultBean bindException(org.springframework.validation.BindException exception) {
        log.info("bindException");
        log.error(exception.getMessage(),exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();

        StringBuffer buffer = new StringBuffer();
        for (ObjectError error : errors) {
            buffer.append(error.getDefaultMessage()).append(";");
        }
        return ApiResultUtil.paramError(buffer.toString());
    }

    /**
     * 权限不足异常处理
     *
     * @param noPermissionException
     * @return
     */
    @ExceptionHandler(NoPermissionException.class)
    public ResultBean noPermissionExceptionHandler(NoPermissionException noPermissionException) {
        log.info("noPermissionExceptionHandler");
        log.error(noPermissionException.getMessage(), noPermissionException);
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
        log.info("commonExceptionHandler");
        log.error(businessException.getMessage(), businessException);
        return businessException.getResultBean();
    }


    /**
     * 表单参数校验异常
     *
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean paramValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.info("paramValidExceptionHandler");
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);

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
        log.info("constraintViolationExceptionHandler");
        log.error(constraintViolationException.getMessage(), constraintViolationException);

        Set<ConstraintViolation<?>> cves = constraintViolationException.getConstraintViolations();
        StringBuffer errorMsg = new StringBuffer();

        for (ConstraintViolation cv : cves) {
            errorMsg.append(cv.getMessageTemplate()).append(";");
        }

        return ApiResultUtil.paramError(errorMsg.toString());
    }



}
