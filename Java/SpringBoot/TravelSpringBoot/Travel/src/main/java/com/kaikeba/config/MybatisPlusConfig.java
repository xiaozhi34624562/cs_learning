package com.kaikeba.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.kaikeba.dao")
public class MybatisPlusConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
