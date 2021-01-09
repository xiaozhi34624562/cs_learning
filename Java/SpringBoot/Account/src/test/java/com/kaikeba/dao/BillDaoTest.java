package com.kaikeba.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Bill;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillDaoTest extends TestCase {

    @Resource
    private BillDao billDao;

    @Test
    public void getByType(){
        billDao.selectByType(1l).forEach(bill -> {
            System.out.println(bill);
        });
    }

    @Test
    public void findAll(){
        billDao.selectAll().forEach(bill -> {
            System.out.println(bill);
        });
    }

    @Test
    public void findPage(){
        Bill b = new Bill();
        b.setTitle("a");
        PageInfo<Bill> all = PageHelper.startPage(1, 20).doSelectPageInfo(() -> {billDao.selectAll();});
        all.getList().forEach(bill -> {
            System.out.println(bill.getTitle());
        });
        System.out.println(all.getPages());
    }

}