package com.kaikeba.dao.imp;

import com.kaikeba.bean.Admin;
import com.kaikeba.dao.BaseAdminDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminDaoMysqlTest {
    BaseAdminDao dao = new AdminDaoMysql();

    @Test
    public void updateLoginTime() {
    }

    @Test
    public void login() {
    }

    @Test
    public void consoleOfAdmin() {
        System.out.println(dao.consoleOfAdmin());
    }

    @Test
    public void findAllAdmin() {
        System.out.println(dao.findAllAdmin(true, 2, 2));
    }

    @Test
    public void findAdminByPhone() {
        System.out.println(dao.findAdminByPhone("13466668856"));
    }

    @Test
    public void insertAdmin() {
        int phone = 8888;
        int sum =0;
        for (int i = 0; i < 50; i++) {
            Admin admin = new Admin("田七"+i,"1346666"+(phone-i), "42112399999999"+(phone-i), "12"+(phone-i));
            int y = dao.insertAdmin(admin) == true ? 1 : 0;
            sum += y;
        }
        System.out.println(sum);
    }

    @Test
    public void deleteAdmin() {
        int phone = 8888;
        int sum =0;
        for (int i = 0; i < 50; i++) {
            Admin admin = new Admin("田七"+i,"1346666"+(phone-i), "421123999999997"+(phone-i), "123"+(phone-i));
            int y = dao.deleteAdmin(phone+"") == true ? 1 : 0;
            sum += y;
        }
        System.out.println(sum);
    }

    @Test
    public void updateAdmin() {
        Admin admin = new Admin("李天","13466664573", "421123999999997000", "1230000");
        boolean y = dao.updateAdmin("13466668855", admin);
        System.out.println(y);
    }
}