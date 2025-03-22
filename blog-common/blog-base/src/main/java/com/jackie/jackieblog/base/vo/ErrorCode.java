package com.jackie.jackieblog.base.vo;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年07月23日 9:05
 * @Description:
 */
public enum ErrorCode {
    PARAMS_ERROR(10001,"参数有误!"),
    BadCredentials(10002, "用户名或者密码输出错误，登陆失败！"),
    TOKEN_ERROR(10003,"token不合法!"),
    ACCOUNT_EXIST(10004,"账号已存在!"),
    ACCOUNT_PWD_NOT_EXIST(10005,"用户名或密码不存在!"),
    Account_FORBIDDEN(10006,"账户被禁用，登陆失败！"),
    Account_LOCKED(1007,"账户被锁定，登陆失败！"),

    NO_PERMISSION(70001,"无访问权限!"),
    SESSION_TIME_OUT(90001,"会话超时!"),
    NO_LOGIN(90002,"未登录!"),
    UNKNOWN_ERROR(90003,"不知名错误，登陆失败！");


    private int code;
    private String msg;


    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;

    }
    public int getCode(){
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
