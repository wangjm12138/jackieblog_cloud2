package com.jackie.blog.article.exception;

import com.jackie.blog.base.exception.ErrorCode;


/**
 * 评论相关错误码
 *
 * @author jackie wang
 */
public enum CommentErrorCode implements ErrorCode {
    /**
     * 重复电话号码
     */
    COMMENT_INSERT_COMMENT_ERROR("INSERT_COMMENT_ERROR", "插入评论失败"),

    /**
     * 用户不存在
     */
    COMMENT_USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在");


    private String code;

    private String message;

    CommentErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

