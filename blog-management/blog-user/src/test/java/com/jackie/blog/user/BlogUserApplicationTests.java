package com.jackie.blog.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BlogUserApplicationTests {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 自动加盐哈希
//    }
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String rawPassword = "123456";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("加密后的密码: " + encodedPassword);
    }

}
