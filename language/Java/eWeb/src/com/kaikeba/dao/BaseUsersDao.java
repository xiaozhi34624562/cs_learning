package com.kaikeba.dao;


import com.kaikeba.bean.Admin;
import com.kaikeba.bean.LazyUser;
import com.kaikeba.bean.Users;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用于定义eadmin表格的操作规范
 */
public interface BaseUsersDao {

    List<LazyUser> getLazyTotal();

    List<LazyUser> getLazyBoard(int num);

    /**
     * 根据用户名，更新登陆时间和登录ip
     * @param username
     */
    void updateLoginTimeOfUser(String username, Date date);

    /**
     * 用于查询取件人员总数和日注册量
     * @return {totalAdmin:总人数, newAdmin:新注册人数}
     */
    Map<String, Integer> consoleOfUser();


    /**
     * 查询全部取件人
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    List<Users> findAllUser(boolean limit, int offset, int pageNumber);

    /**
     * 通过手机号查找到取件人的信息
     * @param phone 手机号
     * @return 快递员信息
     */
    Users findUserByPhone(String phone);

    /**
     * 增加新的取件人
     * @param user 取件人信息
     * @return 增加的结果，true则增加成功，false则添加失败
     */
    boolean insertUser(Users user);

    /**
     * 删除取件人
     * @param phone 取件人信息
     * @return 删除的结果，true则删除成功，false则删除失败
     */
    boolean deleteUser(String phone);

    /**
     * 更新取件人
     * @param user 取件人信息
     * @return 更新的结果，true则更新成功，false则更新失败
     */
    boolean updateUser(String phone, Users user);
}
