package com.kaikeba.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillTypeServiceTest extends TestCase {

    @Resource
    private BillTypeService billTypeService;

    @Test
    public void get(){
        billTypeService.getAll().forEach(billType -> {
            System.out.println(billType);
        });
    }
}