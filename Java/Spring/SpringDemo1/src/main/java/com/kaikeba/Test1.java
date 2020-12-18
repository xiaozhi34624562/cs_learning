package com.kaikeba;

import com.kaikeba.bean.Users;
import com.kaikeba.service.UsersService;
import com.kaikeba.service.impl.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
        UsersService servive = (UsersService) app.getBean("service");
        servive.insertUsers1(new Users());
    }
}
