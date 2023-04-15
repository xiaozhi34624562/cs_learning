package com.kaikeba.dao.imp;

import com.kaikeba.bean.Admin;
import com.kaikeba.bean.LazyUser;
import com.kaikeba.bean.Users;
import com.kaikeba.dao.BaseUsersDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UsersDaoMysql implements BaseUsersDao {
    // 更新登录人员的登录时间
    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE USERS SET LOGINTIME=? WHERE PHONE=?";
    // 查询全部的取件人数和当日新增的取件人人数
    public static final String SQL_NUMBER_OF_ALL_USERS = "SELECT COUNT(ID) AS number_of_users, " +
            "COUNT(TO_DAYS(REGISTERTIME)=TO_DAYS(NOW()) OR NULL) AS new_users FROM USERS;";
    // 查询全部的取件人信息
    public static final String SQL_FIND_ALL_INFORMATION_OF_USERS = "SELECT * FROM USERS;";
    // 用分页查询全部的取件人信息
    public static final String SQL_FIND_ALL_INFORMATION_OF_USERS_IN_LIMIT = "SELECT * FROM USERS LIMIT ?, ?;";
    // 用电话号码查询取件人信息
    public static final String SQL_FIND_USERS_BY_PHONE = "SELECT * FROM USERS WHERE PHONE=?;";
    // 录入取件人信息
    public static final String SQL_INSERT_USERS = "INSERT INTO USERS(USERNAME, PHONE, IDNUMBER, PASSWORD, REGISTERTIME) VALUE(?, ?, ?, ?, ?);";
    // 删除取件人信息
    public static final String SQL_DELETE_USERS = "DELETE FROM USERS WHERE PHONE=?;";
    // 懒人榜
    public static final String SQL_LAZY = "SELECT COUNT(ID) AS NUM, USERNAME FROM (SELECT ID, USERNAME, INTIME FROM EXPRESS WHERE DATE_SUB(CURDATE(), INTERVAL ? DAY) <= DATE(INTIME)) AS S GROUP BY USERNAME ORDER BY NUM DESC;";
    // 懒人榜总榜
    public static final String SQL_LAZY_TOTAL = "SELECT COUNT(ID) AS NUM, USERNAME FROM EXPRESS GROUP BY USERNAME ORDER BY NUM DESC;";

    @Override
    public List<LazyUser> getLazyTotal() {
        List<LazyUser> list = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_LAZY_TOTAL);
            resultSet = state.executeQuery();
            while (resultSet.next()) {
                int num = resultSet.getInt("NUM");
                String name = resultSet.getString("USERNAME");
                list.add(new LazyUser(num, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return list;
    }

    @Override
    public List<LazyUser> getLazyBoard(int days) {
        List<LazyUser> list = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_LAZY);
            state.setInt(1, days);
            resultSet = state.executeQuery();
            while (resultSet.next()) {
                int num = resultSet.getInt("NUM");
                String name = resultSet.getString("USERNAME");
                list.add(new LazyUser(num, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return list;
    }

    @Override
    public void updateLoginTimeOfUser(String phone, Date date) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            state.setString(2, phone);
            state.setDate(1, new java.sql.Date(date.getTime()));
            state.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, null);
        }

    }

    @Override
    public Map<String, Integer> consoleOfUser() {
        Map<String, Integer> data = new HashMap<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_NUMBER_OF_ALL_USERS);
            resultSet = state.executeQuery();
            while (resultSet.next()) {
                int totalUsers = resultSet.getInt("number_of_users");
                int newUsers = resultSet.getInt("new_users");
                data.put("totalUsers", totalUsers);
                data.put("newUsers", newUsers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return data;
    }

    @Override
    public List<Users> findAllUser(boolean limit, int offset, int pageNumber) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        List<Users> list = new ArrayList<>();

        try {
            if (limit) {
                state = conn.prepareStatement(SQL_FIND_ALL_INFORMATION_OF_USERS_IN_LIMIT);
                state.setInt(1, offset);
                state.setInt(2, pageNumber);
            } else {
                state = conn.prepareStatement(SQL_FIND_ALL_INFORMATION_OF_USERS);
            }
            resultSet = state.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                java.sql.Date registerTime = resultSet.getDate("registerTime");
                java.sql.Date loginTime = resultSet.getDate("loginTime");
                Users user = new Users(id, username, phone, password, registerTime, loginTime);
                list.add(user);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return list;
    }

    @Override
    public Users findUserByPhone(String phone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        Users user = null;

        try {
            state = conn.prepareStatement(SQL_FIND_USERS_BY_PHONE);
            state.setString(1, phone);
            resultSet = state.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String newPhone = resultSet.getString("phone");
                String idNumber = resultSet.getString("idNumber");
                String password = resultSet.getString("password");
                user = new Users(username, newPhone, idNumber, password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state,resultSet);
        }
        return user;
    }

    @Override
    public boolean insertUser(Users user) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;

        try {
            // USERNAME=?, PHONE=?, IDNUMBER=?, PASSWORD=?
            state = conn.prepareStatement(SQL_INSERT_USERS);
            state.setString(1, user.getUsername());
            state.setString(2, user.getPhone());
            state.setString(3, user.getIdNumber());
            state.setString(4, user.getPassword());
            java.util.Date date = new Date();//new java.sql.Date(date.getTime())
            state.setDate(5, new java.sql.Date(date.getTime()));
            return state.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, null);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String phone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE_USERS);
            state.setString(1, phone);
            return state.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, null);
        }
        return false;
    }

    @Override
    public boolean updateUser(String phone, Users user) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE_USERS);
            state.setString(1, phone);
            state.executeUpdate();
            state = conn.prepareStatement(SQL_INSERT_USERS);
            state.setString(1, user.getUsername());
            state.setString(2, user.getPhone());
            state.setString(3, user.getIdNumber());
            state.setString(4, user.getPassword());
            java.util.Date date = new Date();//new java.sql.Date(date.getTime())
            state.setDate(5, new java.sql.Date(date.getTime()));
            return state.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, null);
        }
        return false;
    }
}
