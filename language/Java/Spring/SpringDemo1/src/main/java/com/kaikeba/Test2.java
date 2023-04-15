package com.kaikeba;

import com.kaikeba.bean.Users;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
//        Users user1 = (Users) app.getBean("user1");
//        System.out.println(user1);
//
//        Users user2 = (Users) app.getBean("user1");
//        System.out.println(user2);
    }
}
