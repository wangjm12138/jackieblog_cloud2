package com.jackie.blog.auth.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码确认
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 密码确认
     */
    @NotBlank(message = "密码不能为空")
    private String confirmPassword;
}
