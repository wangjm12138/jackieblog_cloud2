package com.jackie.jackieblog.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @作者 Jackie Wang
 * @微信号 ilovepython12138
 * @GitHub https://github.com/wangjm12138
 * @博客 http://www.jackieblog.top
 * @date 2023年02月19日 19:44
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean success;

    private int code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }
}
