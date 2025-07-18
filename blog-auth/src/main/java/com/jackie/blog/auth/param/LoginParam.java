package com.jackie.blog.auth.param;

import lombok.Data;

@Data
public class LoginParam extends RegisterParam {
    /**
     * 记住我
     */
    private Boolean rememberMe;
}
