package com.kaikeba.service.impl;

import com.kaikeba.bean.Users;
import com.kaikeba.dao.UsersDao;
import com.kaikeba.dao.impl.UsersDaoImpl;
import com.kaikeba.service.UsersService;

public class UsersServiceImpl implements UsersService {

    UsersDao dao;


    public void setDao(UsersDao dao) {
        System.out.println("use setter method in service");
        this.dao = dao;
    }


    @Override
    public int insertUsers1(Users user) {
        System.out.println("UsersServiceImpl1");
        dao.insertUser(user);
        return 0;
    }
}
