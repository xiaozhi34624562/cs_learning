package com.kaikeba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class Demo {
    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private JdbcProperties jdbcProperties;

    @GetMapping("/getHello")
    public String getHello(){
        return "hello, spring boot" + dataSource;
    }
}
