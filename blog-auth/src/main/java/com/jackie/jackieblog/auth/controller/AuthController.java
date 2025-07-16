package com.jackie.jackieblog.auth.controller;


import com.jackie.jackieblog.auth.param.RegisterParam;
import com.jackie.jackieblog.base.vo.Result;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/auth")
@RestController
public class AuthController {


    private static final String ROOT_CAPTCHA = "8888";


    @PostMapping("/login")
    public Result login(String username, String password) {
        return Result.success("11");

    }

    @PostMapping("register")
    public Result register(@Valid @RequestBody RegisterParam registerParam) {
        return Result.success("11");

    }
}
