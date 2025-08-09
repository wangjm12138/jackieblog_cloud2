package com.jackie.blog.user.service;

import com.jackie.blog.api.user.response.UserOperatorResponse;
import com.jackie.blog.user.dao.UserServiceMapper;
import com.jackie.blog.user.entity.User;
import com.jackie.blog.user.entity.conventor.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static com.jackie.blog.user.exception.UserErrorCode.*;

@Service
public class UserService {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 自动加盐哈希
//    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceMapper userMapper;


    @Transactional
    public User findUserId(Long userId) {
        // 1. 根据账号查询用户（手机号/邮箱/用户名）
        return userMapper.selectById(userId);
    }

    @Transactional
    public User findAccountPass(String account, String password) {
        // 1. 根据账号查询用户（手机号/邮箱/用户名）
        return userMapper.selectByUsername(account);
    }

    @Transactional
    public UserOperatorResponse findUserIdWrap(Long userId) {
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();

        // 1. 根据账号查询用户（手机号/邮箱/用户名）
        User user = this.findUserId(userId);
        if (user == null) {
            userOperatorResponse.setSuccess(false);
            userOperatorResponse.setResponseCode(USER_NOT_EXIST.getCode());
            userOperatorResponse.setResponseMessage(USER_NOT_EXIST.getMessage());
            return userOperatorResponse;
        }
        userOperatorResponse.setSuccess(true);
        userOperatorResponse.setResponseCode("ok");
        userOperatorResponse.setUser(UserConvertor.INSTANCE.mapToVo(user));
        return userOperatorResponse;
    }

    @Transactional
    public UserOperatorResponse findAccountPassWrap(String account, String password) {
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();

        User user = this.findAccountPass(account, password);
        System.out.println(user);
        if (user == null) {
            userOperatorResponse.setSuccess(false);
            userOperatorResponse.setResponseCode(USER_NOT_EXIST.getCode());
            userOperatorResponse.setResponseMessage(USER_NOT_EXIST.getMessage());
            return userOperatorResponse;
        } else {
            // 2. 校验密码（实际项目需加密比对）
            if (!passwordEncoder.matches(password, user.getPassword())) {
                userOperatorResponse.setResponseCode(PASS_ERROR.getCode());
                userOperatorResponse.setResponseMessage(PASS_ERROR.getMessage());
                return userOperatorResponse;
            }
        }
        userOperatorResponse.setSuccess(true);
        userOperatorResponse.setResponseCode("ok");
        userOperatorResponse.setUser(UserConvertor.INSTANCE.mapToVo(user));
        System.out.println(userOperatorResponse);
        return userOperatorResponse;
    }

    @Transactional
    public UserOperatorResponse register(String username, String password) {
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();

        User existUser = userMapper.selectByUsername(username);
        if (existUser!=null) {
            userOperatorResponse.setSuccess(false);
            userOperatorResponse.setResponseCode(NAME_EXIST.getCode());
            userOperatorResponse.setResponseMessage(NAME_EXIST.getMessage());
            return userOperatorResponse;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setCreateDate(new Date());
        int insert = userMapper.insert(user);
        if (insert != 1) {
            userOperatorResponse.setSuccess(false);
            userOperatorResponse.setResponseCode(USER_CREATE_FAIL.getCode());
            userOperatorResponse.setResponseMessage(USER_CREATE_FAIL.getMessage());
            return userOperatorResponse;
        }

        userOperatorResponse.setSuccess(true);
        userOperatorResponse.setResponseCode("ok");
        userOperatorResponse.setUser(UserConvertor.INSTANCE.mapToVo(user));

        return userOperatorResponse;
    }

}
