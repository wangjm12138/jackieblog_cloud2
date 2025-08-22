package com.jackie.blog.web.handler;

import com.jackie.blog.base.exception.BizException;
import com.jackie.blog.base.exception.SystemException;
import com.jackie.blog.base.vo.Result;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author jackie wang
 */
@RestControllerAdvice
@Slf4j
public class GlobalWebExceptionHandler {

    /**
     * 自定义业务异常处理器
     *
     * @param bizException
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result exceptionHandler(BizException bizException) {
        log.error("bizException occurred.", bizException);
        Result result = new Result();
        result.setCode(bizException.getErrorCode().getCode());
        if (bizException.getMessage() == null) {
            result.setMessage(bizException.getErrorCode().getMessage());
        } else {
            result.setMessage(bizException.getMessage());
        }
        result.setSuccess(false);
        return result;
    }


    /**
     * 自定义系统异常处理器
     *
     * @param systemException
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result systemExceptionHandler(SystemException systemException) {
        System.out.println("systemException occurred.1111111111111111111111");
        log.error("systemException occurred.", systemException);
        Result result = new Result();
        result.setCode(systemException.getErrorCode().getCode());
        if (systemException.getMessage() == null) {
            result.setMessage(systemException.getErrorCode().getMessage());
        } else {
            result.setMessage(systemException.getMessage());
        }
        result.setSuccess(false);
        return result;
    }

}
