package com.jackie.blog.auth.controller;


import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.jackie.blog.api.user.request.UserQueryRequest;
import com.jackie.blog.api.user.response.UserOperatorResponse;
import com.jackie.blog.api.user.response.data.UserInfo;
import com.jackie.blog.auth.exception.AuthException;
import com.jackie.blog.auth.param.LoginParam;
import com.jackie.blog.auth.param.RegisterParam;
import com.jackie.blog.auth.vo.LoginVO;
import com.jackie.blog.base.vo.Result;
import jakarta.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jackie.blog.api.user.request.UserRegisterRequest;
import com.jackie.blog.api.user.service.UserFacadeService;

import static com.jackie.blog.auth.exception.AuthErrorCode.VERIFICATION_CODE_WRONG;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ROOT_CAPTCHA = "8888";

    @DubboReference(version = "1.0.0")
    private UserFacadeService userFacadeService;

    /**
     * 默认登录超时时间：7天
     */
    private static final Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24 * 7;


    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginParam loginParam) {
        System.out.println("111111111");
        String token;
        UserInfo userInfo=null;
        if("password".equals(loginParam.getLoginType())) {
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setAccount(loginParam.getAccount());
            userQueryRequest.setPassword(loginParam.getPassword());
            UserOperatorResponse userOperatorResponse = userFacadeService.query(userQueryRequest);
            userInfo = userOperatorResponse.getUser();
        } else if ("sms".equals(loginParam.getLoginType())) {
            System.out.println("sms");
        } else {
            throw new AuthException(VERIFICATION_CODE_WRONG);
        }
        assert userInfo != null;

        StpUtil.login(userInfo.getUserId(), new SaLoginModel().setIsLastingCookie(loginParam.getRememberMe())
                .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
        StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
        LoginVO loginVO = new LoginVO(userInfo);
        return Result.success(loginVO);

    }

    @PostMapping("register")
    public Result register(@Valid @RequestBody RegisterParam registerParam) {

        //验证码校验
        String cacheCode = stringRedisTemplate.opsForValue().get(registerParam.getTelephone());
        if(!StringUtils.equalsIgnoreCase(cacheCode, registerParam.getCaptcha())){
            throw new AuthException(VERIFICATION_CODE_WRONG);
        }

        //注册
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setTelephone(registerParam.getTelephone());
        userRegisterRequest.setInviteCode(registerParam.getInviteCode());

        UserOperatorResponse register = userFacadeService.register(userRegisterRequest);

        return Result.success("11");

    }
}
