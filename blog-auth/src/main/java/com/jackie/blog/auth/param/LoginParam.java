package com.jackie.blog.auth.param;

import lombok.Data;

@Data
public class LoginParam {

    private String loginType;

    private String account;

    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe;
}
