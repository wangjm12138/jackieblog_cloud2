package com.jackie.blog.base.exception;

/**
 * 错误码
 *
 * @author jackie wang
 */
public interface ErrorCode {
    /**
     * 错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
