package com.kaikeba.service;

import com.kaikeba.bean.Student;
import com.kaikeba.dao.MapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThymeleafService {
    @Autowired
    private MapperDao mapperDao;

    public List<Student> getAll(){
        return mapperDao.selectAll();
    }


}
