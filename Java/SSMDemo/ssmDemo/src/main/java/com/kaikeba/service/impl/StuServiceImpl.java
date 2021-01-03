package com.kaikeba.service.impl;

import com.kaikeba.bean.Student;
import com.kaikeba.dao.StudentMapper;
import com.kaikeba.service.StuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StuServiceImpl implements StuService {
    @Resource
    private StudentMapper studentMapper;


    @Override
    public Student selectByNameAndPasswd(Map map) {
        return studentMapper.selectByNameAndPasswd(map);
    }

    @Override
    public Student selectById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    @Transactional
    public int update(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public int delete(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }
}
