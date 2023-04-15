package com.kaikeba.dao;

import com.kaikeba.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll(){
        return jdbcTemplate.query("select * from student", new BeanPropertyRowMapper<>(Student.class));
    }
}
