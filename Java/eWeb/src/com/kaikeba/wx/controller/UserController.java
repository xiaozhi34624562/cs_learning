package com.kaikeba.wx.controller;

import com.kaikeba.bean.Admin;
import com.kaikeba.bean.LazyUser;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.Users;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.UsersService;
import com.kaikeba.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class UserController {
    @ResponseBody("/wx/loginSms.do")
    public String sendSms(HttpServletRequest request, HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        //String code = RandomUtil.getCode()+"";
        String code = "123456";
//        boolean flag = SMSUtil.send(userPhone, code);
        boolean flag = true;
        Message msg = new Message();
        if(flag){
            //短信发送成功
            msg.setStatus(0);
            msg.setResult("验证码已发送,请查收!");
        }else{
            //短信发送失败
            msg.setStatus(1);
            msg.setResult("验证码下发失败,请检查手机号或稍后再试");
        }
        UserUtil.setLoginSms(request.getSession(),userPhone,code);

        String json = JSONUtil.toJSON(msg);
        return json;
    }
//
    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        String userCode = request.getParameter("code");
        String sysCode = UserUtil.getLoginSms(request.getSession(), userPhone);
        Message msg = new Message();
        if(sysCode == null){
            //这个手机号未获取短信
            msg.setStatus(-1);
            msg.setResult("手机号码未获取短信");
        }else if(sysCode.equals(userCode)){
            //这里手机号码和短信一致 , 登陆成功
            //TODO 这个判断应替换为快递员表格查询手机号的结果
            Users user = new Users();
//            Admin admin = AdminService.findAdminByPhone(userPhone);
//            user = UsersService.findUserByPhone(userPhone);
            // 1 为快递员，0 为用户，-1 为新用户
            if (AdminService.findAdminByPhone(userPhone) != null){
                msg.setStatus(1);
                user.setUser(false);
            } else if (UsersService.findUserByPhone(userPhone) != null) {
                msg.setStatus(0);
                user.setUser(true);
            } else {
                msg.setStatus(1);
                user.setUser(false);
            }

            user.setPhone(userPhone);
            UserUtil.setWxUser(request.getSession(),user);
        }else{
            //这里是验证码不一致 , 登陆失败
            msg.setStatus(-2);
            msg.setResult("验证码不一致,请检查");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/wx/userInfo.do")
    public String userInfo(HttpServletRequest request, HttpServletResponse response){
        Users user = UserUtil.getWxUser(request.getSession());
        boolean isUser = user.isUser();
        Message msg = new Message();
        if(isUser)
            msg.setStatus(0);
        else
            msg.setStatus(1);
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    @ResponseBody("/wx/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //1.    销毁session
        request.getSession().invalidate();
        //2.    给客户端回复消息
        Message msg = new Message(0);
        return JSONUtil.toJSON(msg);
    }

    // 对应wxUserhome.html, 先判断是新人，还是用户，还是快递员
    @ResponseBody("/wx/userHome.do")
    public String showInfo(HttpServletRequest request, HttpServletResponse response){
        Users user = UserUtil.getWxUser(request.getSession());
        String phone = user.getPhone();
        Map<String, Object> map = new HashMap<>();
        Message message = new Message();
        if (user.isUser() == true){
            // 0 已经注册的用户
            user = UsersService.findUserByPhone(phone);
            user.setUser(true);
            message.setStatus(0);
            message.setResult("你好, 用户"+user.getUsername());
            message.setData(user.getUsername());
            map.put("type", 0);
            map.put("user", user);
        } else if (AdminService.findAdminByPhone(phone) != null){
            // 1 表示已经注册的快递员
            Admin admin = AdminService.findAdminByPhone(phone);
            message.setStatus(1);
            message.setResult("你好，快递员"+admin.getUsername());
            message.setData(admin.getUsername());
            map.put("type", 1);
            map.put("admin", admin);
        } else {
            // -1 表示这是新用户
            message.setStatus(-1);
            message.setResult("你好，新用户，欢迎注册资料");
            map.put("type", -1);
            map.put("new", phone);
        }
        UserUtil.setWxAccount(request.getSession(), map);
        String json = JSONUtil.toJSON(message);
        return json;
    }

//    // 如果是新用户，则需要认证，老用户不需要认证
//    @ResponseBody("/wx/conform.do")
//    public String conform(HttpServletResponse response, HttpServletRequest request){
//        System.out.println("开始了");
//        Map map = UserUtil.getWxAccount(request.getSession());
//        System.out.println(map);
//        Message message = new Message();
//        if (map.isEmpty()) {
//            message.setStatus(-2);
//            message.setResult("请重新登录");
//            // 0 是用户； 1 是快递员； -1；新用户
//        } else if (map.get("type").equals(0)){
//            message.setStatus(0);
//            message.setResult("你好，你已经注册过了");
//        } else if (map.get("type").equals(1)){
//            message.setStatus(1);
//            message.setResult("你好，你已经注册过了");
//        } else {
//            message.setStatus(-1);
//        }
//        String json = JSONUtil.toJSON(message);
//        return json;
//    }

    // 获取认证信息
    @ResponseBody("/wx/wxRegisterInfo.do")
    public String wxRegister(HttpServletRequest request, HttpServletResponse response){
        String userPhone = request.getParameter("phone");
        //String code = RandomUtil.getCode()+"";
        String code = "666";
        //*******************************************************************************
//        boolean flag = SMSUtil.send(userPhone, code);
        boolean flag = true;
        Message msg = new Message();
        if(flag){
            //短信发送成功
            msg.setStatus(0);
            msg.setResult("验证码已发送,请查收!");
        }else{
            //短信发送失败
            msg.setStatus(1);
            msg.setResult("验证码下发失败,请检查手机号或稍后再试");
        }
        request.getSession().setAttribute(userPhone, code);

        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/addAccount.do")
    public String addAccount(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String idNumber = request.getParameter("idNumber");
        String msgCode = request.getParameter("msgCode");
        int status = Integer.parseInt(request.getParameter("status"));
        String sysCode = (String)request.getSession().getAttribute(phone);
        Message msg = new Message();
        if (sysCode == null) {
            //这个手机号未获取短信
            msg.setStatus(-1);
            msg.setResult("手机号码未获取短信");
        } else if (sysCode.equals(msgCode)) {
            java.util.Date date = new Date();
            if (status == 1){
                boolean result = AdminService.insertAdmin(new Admin(userName, phone, idNumber,password, new java.sql.Date(date.getTime())));
                System.out.println("增加了一个快递员"+result);
            } else {
                boolean result = UsersService.insertUser(new Users(userName, phone,  password, new java.sql.Date(date.getTime()),new java.sql.Date(date.getTime()), idNumber ));
            }
            msg.setStatus(0);
            msg.setResult("恭喜你，修改成功");
        } else {
            //这里是验证码不一致 , 登陆失败
            msg.setStatus(-2);
            msg.setResult("验证码不一致,请检查");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/lazy.do")
    public String lazyInRank(HttpServletRequest request, HttpServletResponse response){
       List<LazyUser> listMonth = UsersService.getLazyBoard(30);
       List<LazyUser> listYear = UsersService.getLazyBoard(365);
       List<LazyUser> listTotal = UsersService.getLazyTotal();
       Map<String, Object> map = new HashMap<>();
       map.put("month1", listMonth.toArray()[0]);
       map.put("month2", listMonth.toArray()[1]);
       map.put("month3", listMonth.toArray()[2]);
       map.put("year1", listYear.toArray()[0]);
       map.put("year2", listYear.toArray()[1]);
       map.put("year3", listYear.toArray()[2]);
       map.put("total1", listTotal.toArray()[0]);
       map.put("total2", listTotal.toArray()[1]);
       map.put("total3", listTotal.toArray()[2]);
       String json = JSONUtil.toJSON(map);
       System.out.println(json);
       return json;

    }

}
