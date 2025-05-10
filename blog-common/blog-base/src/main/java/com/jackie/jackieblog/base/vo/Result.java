package com.jackie.jackieblog.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.jackie.jackieblog.base.response.ResponseCode.SUCCESS;

/**
 * @作者 Jackie Wang
 * @微信号 ilovepython12138
 * @GitHub https://github.com/wangjm12138
 * @博客 http://www.jackieblog.top
 * @date 2023年02月19日 19:44
 */
@Data
public class Result<T> {

    /**
     * 状态吗
     */
    private String code;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 消息描述
     */
    private String message;

    /**
     * 数据，可以是任何类型的VO
     */
    private T data;

    public Result() {
    }

    public Result(Boolean success, String code, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, SUCCESS.name(), SUCCESS.name(), data);
    }

    public static <T> Result<T> fail(String errorCode, String message) {
        return new Result<>(false, errorCode, message, null);
    }
}
