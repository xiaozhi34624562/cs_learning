package com.kaikeba.service;

import com.kaikeba.bean.Student;

import java.util.Map;


public interface StuService {

    Student selectByNameAndPasswd(Map map);

    Student selectById(Integer id);

    int insert(Student student);

    int update(Student student);

    int delete(Integer id);

}
