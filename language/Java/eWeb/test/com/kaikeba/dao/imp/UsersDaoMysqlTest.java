package com.kaikeba.dao.imp;

import com.kaikeba.bean.Users;
import com.kaikeba.service.UsersService;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersDaoMysqlTest {

    @Test
    public void updateLoginTimeOfUser() {
    }

    @Test
    public void consoleOfUser() {
        System.out.println(UsersService.consoleOfUser());
    }

    @Test
    public void findAllUser() {
        System.out.println(UsersService.findAllUser(true,2,2 ));
    }

    @Test
    public void findUserByPhone() {
        System.out.println(UsersService.findUserByPhone("13466238840").toString());
    }

    @Test
    public void insertUser() {
        int sum = 0;
        for (int i = 0; i < 50; i++){
            Users user = new Users("李四"+i, "1346623"+(8888-i), "42112610000000"+(8888-i), "12"+(6666-i));
            int y = UsersService.insertUser(user) == true ? 1 : 0;
            sum += y;
        }
        System.out.println(sum);

    }

    @Test
    public void deleteUser() {
        System.out.println(UsersService.deleteUser("126617779"));
    }

    @Test
    public void updateUser() {
        System.out.println(UsersService.updateUser("13466238839",new Users("无极","13400000021", "400022002200220022", "235114")));
    }
}