package com.kaikeba.dao.imp;

import com.kaikeba.bean.Admin;
import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.util.DruidUtil;
import com.kaikeba.util.UserUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class AdminDaoMysql implements BaseAdminDao {
    // 更新登录人员的登录时间
    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE EADMIN SET LOGINTIME=? WHERE USERNAME=?";
    // 通过用户名和密码进行登录
    private static final String SQL_LOGIN = "SELECT ID FROM EADMIN WHERE USERNAME=? AND PASSWORD=?";
    // 查询全部的快递员人数和当日新增的快递员人数
    public static final String SQL_NUMBER_OF_ALL_ADMIN = "SELECT COUNT(ID) AS number_of_admin, " +
            "COUNT(TO_DAYS(REGISTERTIME)=TO_DAYS(NOW()) OR NULL) AS new_Admin FROM EADMIN;";
    // 查询全部的快递员信息
    public static final String SQL_FIND_ALL_INFORMATION_OF_ADMIN = "SELECT * FROM EADMIN;";
    // 用分页查询全部的快递员信息
    public static final String SQL_FIND_ALL_INFORMATION_OF_ADMIN_IN_LIMIT = "SELECT * FROM EADMIN LIMIT ?, ?;";
    // 用电话号码查询快递员信息
    public static final String SQL_FIND_ADMIN_BY_PHONE = "SELECT * FROM EADMIN WHERE PHONE=?;";
    // 录入快递员信息
    public static final String SQL_INSERT_ADMIN = "INSERT INTO EADMIN(USERNAME, PHONE, IDNUMBER, PASSWORD, REGISTERTIME) VALUE(?, ?, ?, ?, ?);";
    // 修改快递员信息 先删除再插入

    // 删除快递员信息
    public static final String SQL_DELETE_ADMIN = "DELETE FROM EADMIN WHERE PHONE=?;";

    /**
     * 根据用户名，更新登陆时间和登录ip
     *
     * @param username
     * @param date
//     * @param ip
     */
    @Override
    public void updateLoginTime(String username, Date date) {
        //1.    获取连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            //3.    填充参数
            state.setDate(1,new java.sql.Date(date.getTime()));
//            state.setString(2,ip);
            state.setString(2,username);
            //4.    执行
            state.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
    }

    /**
     * 管理员根据账号密码登陆
     *
     * @param username 账号
     * @param password 密码
     * @return 登陆的结果， true表示登陆成功
     */
    @Override
    public boolean login(String username, String password) {
        //1.    获取连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_LOGIN);
            //3.    填充参数
            state.setString(1,username);
            state.setString(2,password);
            //4.    执行并获取结果
            rs = state.executeQuery();
            //5.    根据查询结果，返回
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    释放资源
            DruidUtil.close(conn,state,rs);
        }
        return false;
    }

    /**
     * 用于获得总的快递员数量和今日注册的快递员数量
     *
     * @return {totalAdmin:总人数, newAdmin:新注册人数}
     */
    @Override
    public Map<String, Integer> consoleOfAdmin() {
        Map<String, Integer> data = new HashMap<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.prepareStatement(SQL_NUMBER_OF_ALL_ADMIN);
            resultSet = state.executeQuery();
            while (resultSet.next()) {
                int totalAdmin = resultSet.getInt("number_of_admin");
                int newAdmin = resultSet.getInt("new_Admin");
                data.put("totalAdmin", totalAdmin);
                data.put("newAdmin", newAdmin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return data;
    }

    /**
     * limit为true时，分页获取快递员信息
     * limit为false时，直接获取全部信息
     * @param limit
     * @param offset
     * @param pageNumber
     * @return 全部信息的list
     */
    @Override
    public List<Admin> findAllAdmin(boolean limit, int offset, int pageNumber) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        List<Admin> list = new ArrayList<>();

        try {
            if (limit) {
                state = conn.prepareStatement(SQL_FIND_ALL_INFORMATION_OF_ADMIN_IN_LIMIT);
                state.setInt(1, offset);
                state.setInt(2, pageNumber);
            } else {
                state = conn.prepareStatement(SQL_FIND_ALL_INFORMATION_OF_ADMIN);
            }
            resultSet = state.executeQuery();
            //     public Admin(int id, String username, String phone, String idNumber, String password,
            //     int packageNumber, Timestamp registerTime, Timestamp loginTime) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String phone = resultSet.getString("phone");
                String idNumber = resultSet.getString("idNumber");
                String password = resultSet.getString("password");
                int packageNumber = resultSet.getInt("packageNumber");
                java.sql.Date registerTime = resultSet.getDate("registerTime");
                java.sql.Date loginTime = resultSet.getDate("loginTime");
                Admin admin = new Admin(id, username, phone, idNumber, password, packageNumber, registerTime, loginTime);
                list.add(admin);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, resultSet);
        }
        return list;
    }

    /**
     * 通过手机号查找快递员的信息
     * @param phone 手机号
     * @return Admin
     */
    @Override
    public Admin findAdminByPhone(String phone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet resultSet = null;
        Admin admin = null;

        try {
            state = conn.prepareStatement(SQL_FIND_ADMIN_BY_PHONE);
            state.setString(1, phone);
            resultSet = state.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String newPhone = resultSet.getString("phone");
                String idNumber = resultSet.getString("idNumber");
                String password = resultSet.getString("password");
                admin = new Admin(username, newPhone, idNumber, password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state,resultSet);
        }
        return admin;
    }

    /**
     * 添加新的快递员的信息
     * @param admin 快递员信息
     * @return
     */
    @Override
    public boolean insertAdmin(Admin admin) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;

        try {
            // USERNAME=?, PHONE=?, IDNUMBER=?, PASSWORD=?
            state = conn.prepareStatement(SQL_INSERT_ADMIN);
            state.setString(1, admin.getUsername());
            state.setString(2, admin.getPhone());
            state.setString(3, admin.getIdNumber());
            state.setString(4, admin.getPassword());
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

    /**
     * 删除快递员信息
     * @param phone
     * @return
     */
    @Override
    public boolean deleteAdmin(String phone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE_ADMIN);
            state.setString(1, phone);
            return state.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, null);
        }
        return false;
    }

    /**
     * 修改快递员的信息
     * @param phone 错误的快递员信息的手机号
     * @param admin 正确的快递员信息
     * @return
     */
    @Override
    public boolean updateAdmin(String phone, Admin admin) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE_ADMIN);
            state.setString(1, phone);
            state.executeUpdate();
            state = conn.prepareStatement(SQL_INSERT_ADMIN);
            state.setString(1, admin.getUsername());
            state.setString(2, admin.getPhone());
            state.setString(3, admin.getIdNumber());
            state.setString(4, admin.getPassword());
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
