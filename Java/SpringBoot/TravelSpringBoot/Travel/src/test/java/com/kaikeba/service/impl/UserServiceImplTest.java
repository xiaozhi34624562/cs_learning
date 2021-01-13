package com.kaikeba.service.impl;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.User;
import com.kaikeba.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.sql.Date;
import java.util.Arrays;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    public void userTest(){
        User u = new User();
        PageInfo<User> pageInfo = userService.findPage(u, 1, 3);
        pageInfo.getList().forEach(System.out :: println);

        System.out.println("总行数=" + pageInfo.getTotal());
        System.out.println("当前页=" + pageInfo.getPageNum());
        System.out.println("每页行数=" + pageInfo.getPageSize());
        System.out.println("总页数=" + pageInfo.getPages());
        System.out.println("起始行数=" + pageInfo.getStartRow());
        System.out.println("是第一页=" + pageInfo.isIsFirstPage());
        System.out.println("是最后页=" + pageInfo.isIsLastPage());
        System.out.println("还有下一页=" + pageInfo.isHasNextPage());
        System.out.println("还有上一页=" + pageInfo.isHasPreviousPage());
        System.out.println("页码列表" + Arrays.toString(pageInfo.getNavigatepageNums()));

    }

    @Test
    public void userAdd() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("王"+i);
            Date date = new Date(1234123412);
            user.setBirthday(date);
            user.setEmail(i + "q@163.com");
            user.setSex("男");
            user.setTelephone("134888822"+i);
            user.setUsername("wang"+i);
            user.setPassword("1234"+i);
            int add = userService.add(user);
            System.out.println(add);
        }
    }

}