package com.kaikeba.dao;

import com.kaikeba.bean.Student;

import java.util.Map;

public interface StudentMapper {
    Student selectByNameAndPasswd(Map map);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}