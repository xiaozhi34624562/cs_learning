package com.kaikeba.dao;

import com.kaikeba.bean.Student;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mapperDaoTest extends TestCase {
    @Autowired
    private MapperDao mapperDao;

    @Test
    public void testfind1(){
        mapperDao.selectAll().forEach(s -> {
            System.out.println(s);
        });
    }

    @Test
    public void testFind2(){
        Student student = mapperDao.selectByPrimaryKey(2);
        System.out.println(student);
    }

    // 模糊查询
    @Test
    public void testFind3(){
        Example example = new Example(Student.class);
        example.createCriteria().andLike("name", "%二%");
        mapperDao.selectByExample(example).forEach(student -> {
            System.out.println(student);
        });
    }

}