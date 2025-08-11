package com.jackie.blog.auth.controller;


import cn.dev33.satoken.session.SaSession;
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

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.jackie.blog.api.user.request.UserRegisterRequest;
import com.jackie.blog.api.user.service.UserFacadeService;

import static com.jackie.blog.auth.exception.AuthErrorCode.VERIFICATION_CODE_WRONG;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ROOT_CAPTCHA = "8888";

    @DubboReference(version = "1.0.0")
    private UserFacadeService userFacadeService;

    /**
     * 默认登录超时时间：7天
     */
    private static final Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24 * 7;

//    @GetMapping("/test")
//    public Result<Boolean> test() throws Exception {
//        UserOperatorResponse userOperatorResponse = userFacadeService.test();
//        return Result.success(true);
//    }
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginParam loginParam) throws Exception {
        UserInfo userInfo = null;
        UserOperatorResponse userOperatorResponse = null;
        if("password".equals(loginParam.getLoginType())) {
            UserQueryRequest userQueryRequest = new UserQueryRequest(loginParam.getAccount(), loginParam.getPassword());
            userOperatorResponse = userFacadeService.query(userQueryRequest);
        } else if ("sms".equals(loginParam.getLoginType())) {
            System.out.println("sms");
        } else {
            System.out.println("exception");

            throw new AuthException(VERIFICATION_CODE_WRONG);
        }
        assert userOperatorResponse != null;
        if(!userOperatorResponse.getSuccess()){
            return Result.fail(userOperatorResponse.getResponseCode(), userOperatorResponse.getResponseMessage());
        }

        userInfo = userOperatorResponse.getUser();
        SaLoginModel saLoginModel = new SaLoginModel();
        saLoginModel.setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT);
        saLoginModel.setIsLastingCookie(loginParam.getRememberMe());
        StpUtil.login(userInfo.getUserId(), saLoginModel);
        // 设置token-session会话过期时间，按理说是和token一样过期时间，但是这里不知道不一致
        SaSession tokenSession = StpUtil.getTokenSession();
        tokenSession.updateTimeout(saLoginModel.getTimeout());
        StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
        LoginVO loginVO = new LoginVO(userInfo);
        return Result.success(loginVO);

    }

    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterParam registerParam) {
        //注册
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(registerParam.getUsername());
        userRegisterRequest.setPassword(registerParam.getPassword());

        UserOperatorResponse register = userFacadeService.register(userRegisterRequest);
        if(!register.getSuccess()) {
            return Result.fail(register.getResponseCode(), register.getResponseMessage());
        }
        return Result.success(null);

    }

    @PostMapping("/logout")
    public Result<Boolean> logout(){
        StpUtil.logout();
        return Result.success(true);
    }
}
