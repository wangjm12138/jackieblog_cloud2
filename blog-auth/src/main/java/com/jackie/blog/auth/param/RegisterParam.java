package com.jackie.blog.auth.param;

import com.jackie.blog.base.validator.IsMobile;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterParam {

    /**
     * 手机号
     */
    @IsMobile
    private String telephone;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    /**
     * 邀请码
     */
    private String inviteCode;
}
