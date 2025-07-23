package com.jackie.blog.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jackie.blog.user.dao")
@EnableDubbo
public class BlogUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }

}
