package com.kaikeba.service;

import com.kaikeba.bean.Admin;
import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.dao.imp.AdminDaoMysql;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdminService {

    private static BaseAdminDao dao = new AdminDaoMysql();

    /**
     * 更新登陆时间与ip
     * @param username
     * @param date
//     * @param ip
     */
    public static void updateLoginTimeAndIP(String username, Date date){
        dao.updateLoginTime(username,date);
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @return true表示成功，false表示登陆失败
     */
    public static boolean login(String username,String password){
        return dao.login(username,password);
    }

    /**
     * 用于获得总的快递员数量和今日注册的快递员数量
     *
     * @return {totalAdmin:总人数, newAdmin:新注册人数}
     */
    public static Map<String, Integer> consoleOfAdmin(){
        return dao.consoleOfAdmin();
    }

    /**
     * limit为true时，分页获取快递员信息
     * limit为false时，直接获取全部信息
     * @param limit
     * @param offset
     * @param pageNumber
     * @return 全部信息的list
     */
    public static List<Admin> findAllAdmin(boolean limit, int offset, int pageNumber){
        return dao.findAllAdmin(limit, offset, pageNumber);
    }

    /**
     * 通过手机号查找快递员的信息
     * @param phone 手机号
     * @return Admin
     */
    public static Admin findAdminByPhone(String phone){
        return dao.findAdminByPhone(phone);
    }

    /**
     * 添加新的快递员的信息
     * @param admin 快递员信息
     * @return
     */
    public static boolean insertAdmin(Admin admin){
        return dao.insertAdmin(admin);
    }

    /**
     * 删除快递员信息
     *
     * @param phone 快递员信息
     * @return
     */
    public static boolean deleteAdmin(String phone) {
        return dao.deleteAdmin(phone);
    }

    /**
     * 修改快递员的信息
     * @param phone 错误的快递员信息的手机号
     * @param admin 正确的快递员信息
     * @return
     */
    public static boolean updateAdmin(String phone, Admin admin){
        return dao.updateAdmin(phone, admin);
    }

}
