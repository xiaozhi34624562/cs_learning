package com.kaikeba.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 有三种方式注入
 */
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig2 {
    // 方法一
//    @Bean
//    public DataSource dataSource(JdbcProperties jdbcProperties){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
//        dataSource.setUrl(jdbcProperties.getUrl());
//        dataSource.setUsername(jdbcProperties.getUsername());
//        dataSource.setPassword(jdbcProperties.getPassword());
//        return dataSource;
//    }

     //方法二
    @Autowired
    private JdbcProperties jdbcProperties;

//     方法三
    private JdbcProperties jdbcProperties;
    public JdbcConfig2(JdbcProperties jdbcProperties){
        this.jdbcProperties = jdbcProperties;
    }
}
