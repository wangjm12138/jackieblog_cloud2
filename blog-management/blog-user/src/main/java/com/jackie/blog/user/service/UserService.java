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

import static com.jackie.blog.user.exception.UserErrorCode.NAME_EXIST;
import static com.jackie.blog.user.exception.UserErrorCode.USER_CREATE_FAIL;

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
    public UserOperatorResponse query(String account, String password) {
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();

        // 1. 根据账号查询用户（手机号/邮箱/用户名）
        User user = userMapper.selectByPhone(account)
                .or(() -> userMapper.selectByEmail(account))
                .or(() -> userMapper.selectByUsername(account))
                .orElseThrow(() -> {
                    userOperatorResponse.setResponseCode("ok");
                    return null;
                });

        // 2. 校验密码（实际项目需加密比对）
        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new Exception("密码错误");
        }
        userOperatorResponse.setSuccess(true);
        userOperatorResponse.setResponseCode("ok");
        userOperatorResponse.setUser(UserConvertor.INSTANCE.mapToVo(user));
        return userOperatorResponse;
    }

    @Transactional
    public UserOperatorResponse register(String username, String password) {
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();

        Optional<User> existUser = userMapper.selectByUsername(username);
        if (existUser.isPresent()) {
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
