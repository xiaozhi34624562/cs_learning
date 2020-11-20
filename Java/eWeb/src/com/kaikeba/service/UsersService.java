package com.kaikeba.service;

import com.kaikeba.bean.LazyUser;
import com.kaikeba.bean.Users;
import com.kaikeba.dao.BaseUsersDao;
import com.kaikeba.dao.imp.UsersDaoMysql;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class UsersService {
    private static BaseUsersDao dao = new UsersDaoMysql();

    public static void updateLoginTimeOfUser(String username, Date date) {
        dao.updateLoginTimeOfUser(username, date);
    }

    public static Map<String, Integer> consoleOfUser() {
        return dao.consoleOfUser();
    }

    public static List<Users> findAllUser(boolean limit, int offset, int pageNumber) {
        return dao.findAllUser(limit, offset, pageNumber);
    }

    public static Users findUserByPhone(String phone) {
        return dao.findUserByPhone(phone);
    }

    public static boolean insertUser(Users user) {
        return dao.insertUser(user);
    }

    public static boolean deleteUser(String phone) {
        return dao.deleteUser(phone);
    }

    public static boolean updateUser(String phone, Users user) {
        return dao.updateUser(phone, user);
    }

    public static List<LazyUser> getLazyBoard(int num) {
        return dao.getLazyBoard(num);
    }

    public static List<LazyUser> getLazyTotal() {
        return dao.getLazyTotal();
    }
}
