package com.jackie.blog.user.service;

import com.jackie.blog.api.user.response.UserOperatorResponse;
import com.jackie.blog.user.dao.SysUserServiceMapper;
import com.jackie.blog.user.entity.User;
import com.jackie.blog.user.entity.conventor.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 自动加盐哈希
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SysUserServiceMapper userMapper;


    @Transactional
    public UserOperatorResponse query(String account, String password) {
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();

        // 1. 根据账号查询用户（手机号/邮箱/用户名）
        User user = userMapper.selectByPhone(account)
                .or(() -> userMapper.selectByEmail(account))
                .or(() -> userMapper.selectByUsername(account))
                .orElseThrow(() -> {
                    userOperatorResponse.setResponseCode("200");
                    return null;
                });

        // 2. 校验密码（实际项目需加密比对）
        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new Exception("密码错误");
        }
        userOperatorResponse.setSuccess(true);
        userOperatorResponse.setResponseCode("200");
        userOperatorResponse.setUser(UserConvertor.INSTANCE.mapToVo(user));
        return userOperatorResponse;
    }

    @Transactional
    public UserOperatorResponse register(String telephone, String inviteCode) {

        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();
        userOperatorResponse.setSuccess(true);

        return userOperatorResponse;
    }

}
