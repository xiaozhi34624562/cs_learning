package com.kaikeba.dao.impl;

import com.kaikeba.bean.Users;
import com.kaikeba.dao.UsersDao;

public class UsersDaoImpl implements UsersDao {
    @Override
    public int insertUser(Users user) {
        System.out.println("usersDaoImpl in dao");
        return 0;
    }
}
