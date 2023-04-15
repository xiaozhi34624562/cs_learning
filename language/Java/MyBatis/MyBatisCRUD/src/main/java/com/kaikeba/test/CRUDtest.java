package com.kaikeba.test;

import com.kaikeba.bean.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDtest {
    public static void main(String[] args) {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory build = builder.build(reader);
            SqlSession session = build.openSession();
            // 展示全部的数据
            List<Student> students = session.selectList("com.kaikeba.dao.StudentDao.findAll");
            for (Student s: students) {
                System.out.println(s);
            }
            System.out.println("----------------------------------------------");
            // 插入新数据，完整数据
            Student stu1 = new Student(31, "阿斯顿", 25, "10099");
            int num1 = session.insert("com.kaikeba.dao.StudentDao.insertStu1", stu1);
            System.out.println("插入数据，完整 " + num1);
            System.out.println("----------------------------------------------");

            // 插入新数据，缺主键
            Student stu2 = new Student("夏二分", 20, "10202");
            int num2 = session.insert("com.kaikeba.dao.StudentDao.insertStu2", stu2);
            System.out.println("插入数据，缺主键 " + num2);
            System.out.println("----------------------------------------------");

            // 插入新数据，用map
            Map map1 = new HashMap();
            map1.put("name", "绿豆糕");
            map1.put("age", 29);
            map1.put("number", "10202");
            int num3 = session.insert("com.kaikeba.dao.StudentDao.insertStu3", map1);
            System.out.println("插入数据，用map " + num3);
            System.out.println("----------------------------------------------");

            // 删除数据，用id
            int id = 3;
            int num4 = session.delete("com.kaikeba.dao.StudentDao.deleteStu", id);
            System.out.println("删除数据，"+ num4);
            System.out.println("----------------------------------------------");

            // 修改数据，完整数据
            Student stu3 = new Student(4, "风",21, "10302");
            int num5 = session.update("com.kaikeba.dao.StudentDao.updateStu1", stu3);
            System.out.println("修改数据，完整" + num5);
            System.out.println("----------------------------------------------");

            // 修改数据，用map
            Map map2 = new HashMap();
            map2.put("name", "猪八戒");
            map2.put("id", 1);
            int num6 = session.update("com.kaikeba.dao.StudentDao.updateStu2", map2);
            System.out.println("修改数据，用map" + num6);

            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
