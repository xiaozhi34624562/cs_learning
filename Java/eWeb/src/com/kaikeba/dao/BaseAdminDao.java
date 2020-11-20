package com.kaikeba.dao;


import com.kaikeba.bean.Admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用于定义eadmin表格的操作规范
 */
public interface BaseAdminDao {

    /**
     * 根据用户名，更新登陆时间和登录ip
     * @param username
     */
    void updateLoginTime(String username, Date date);

    /**
     * 管理员根据账号密码登陆
     * @param username 账号
     * @param password 密码
     * @return 登陆的结果， true表示登陆成功
     */
    boolean login(String username,String password);

    /**
     * 用于查询快递人员总数和日注册量
     * @return {totalAdmin:总人数, newAdmin:新注册人数}
     */
    Map<String, Integer> consoleOfAdmin();

    List<Admin> findAllAdmin(boolean limit, int offset, int pageNumber);

    /**
     * 通过手机号查找到快递员的信息
     * @param phone 手机号
     * @return 快递员信息
     */
    Admin findAdminByPhone(String phone);

    /**
     * 增加新的快递员
     * @param admin 快递员信息
     * @return 增加的结果，true则增加成功，false则添加失败
     */
    boolean insertAdmin(Admin admin);

    /**
     * 删除快递员
     * @param phone 快递员电话
     * @return 删除的结果，true则删除成功，false则删除失败
     */
    boolean deleteAdmin(String phone);

    /**
     * 更新快递员
     * @param admin 快递员信息
     * @return 更新的结果，true则更新成功，false则更新失败
     */
    boolean updateAdmin(String phone, Admin admin);
}
