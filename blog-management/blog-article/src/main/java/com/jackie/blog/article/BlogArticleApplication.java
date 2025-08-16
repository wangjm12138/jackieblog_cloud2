package com.jackie.blog.article;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.jackie.blog.article.dao")
@EnableDiscoveryClient
@EnableDubbo
public class BlogArticleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogArticleApplication.class, args);
        MybatisPlusProperties properties = context.getBean(MybatisPlusProperties.class);
    }

}
