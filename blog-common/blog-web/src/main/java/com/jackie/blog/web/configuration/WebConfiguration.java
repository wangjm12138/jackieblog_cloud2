package com.jackie.blog.web.configuration;

import com.jackie.blog.web.handler.GlobalWebExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jackie wang
 */
@AutoConfiguration
@ConditionalOnWebApplication
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean
    GlobalWebExceptionHandler globalWebExceptionHandler() {
        return new GlobalWebExceptionHandler();
    }

}
