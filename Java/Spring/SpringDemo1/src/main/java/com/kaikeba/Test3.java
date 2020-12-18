package com.kaikeba;

import com.kaikeba.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test3 {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("application2.xml");
        Student stu1 = (Student) app.getBean("stu1");
        System.out.println(stu1);
        Student stu2 = (Student) app.getBean("stu2");
        System.out.println(stu2);
        Student stu3 = (Student) app.getBean("stu3");
        System.out.println(stu3);
        Student stu4 = (Student) app.getBean("stu4");
        System.out.println(stu4);

    }
}
