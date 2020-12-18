package com.kaikeba.dao;

import com.kaikeba.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public List<Student> findAll();

    // student信息完整
    public int insertStu1(Student student);

    // student信息缺少主键
    public int insertStu2(Student student);

    // 多个参数
    public int insertStu3(Map map);

    public int deleteStu(int id);

    public int updateStu1(Student student);

    public int updateStu2(Map map);
}
