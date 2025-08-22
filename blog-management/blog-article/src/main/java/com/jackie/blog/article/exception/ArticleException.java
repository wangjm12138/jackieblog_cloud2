package com.jackie.blog.article.exception;

import com.jackie.blog.base.exception.BizException;
import com.jackie.blog.base.exception.ErrorCode;

/**
 * 认证异常
 *
 * @author jackie wang
 */
public class ArticleException extends BizException {
    public ArticleException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ArticleException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ArticleException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public ArticleException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public ArticleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
