package com.jackie.blog.user;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.jackie.blog.user.dao")
@EnableDubbo
public class BlogUserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogUserApplication.class, args);
        MybatisPlusProperties properties = context.getBean(MybatisPlusProperties.class);
        System.out.println("MyBatis-Plus ID 策略: " + properties.getGlobalConfig().getDbConfig().getIdType());
    }

}
