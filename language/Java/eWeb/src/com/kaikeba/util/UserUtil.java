package com.kaikeba.util;

import com.kaikeba.bean.Users;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UserUtil {
    public static String getUserName(HttpSession session){
        return (String) session.getAttribute("adminUserName");
    }
    public static String getUserPhone(HttpSession session){
        // TODO : 还没有编写柜子端,未存储任何的录入人信息
        return "18888888888";
    }
    public static String getLoginSms(HttpSession session,String userPhone){
        return (String) session.getAttribute(userPhone);
    }
    public static void setLoginSms(HttpSession session,String userPhone,String code){
        session.setAttribute(userPhone,code);
    }
    public static void setWxUser(HttpSession session, Users user){
        session.setAttribute("wxUser",user);
    }
    public static Users getWxUser(HttpSession session){
        return  (Users) session.getAttribute("wxUser");
    }
    public static void setWxAccount(HttpSession session, Map map){
        session.setAttribute("wxAccount",map);
    }
    public static HashMap getWxAccount(HttpSession session){
        return (HashMap) session.getAttribute("wxAccount");
    }

}
