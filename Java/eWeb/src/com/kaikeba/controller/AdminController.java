package com.kaikeba.controller;

import com.kaikeba.bean.Admin;
import com.kaikeba.bean.BootStrapTableAdmin;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminController {

    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        //1.    接参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.    调用Service传参数，并获取结果
        boolean result = AdminService.login(username, password);
        //3.    根据结果，准备不同的返回数据
        Message msg = null;
        if (result) {
            msg = new Message(0, "登录成功");//{status:0,result:"登录成功"}
            //登录时间 和 ip的更新
            Date date = new Date();
//            String ip = request.getRemoteAddr();
//            AdminService.updateLoginTimeAndIP(username,date,ip);
            AdminService.updateLoginTimeAndIP(username, date);
            request.getSession().setAttribute("adminUserName", "username");
        } else {
            msg = new Message(-1, "登录失败");//{status:-1,result:"登录失败"}
        }
        //4.    将数据转换为JSON
        String json = JSONUtil.toJSON(msg);
        //5.    将JSON回复给ajax
        return json;
    }

    @ResponseBody("/admin/insert.do")
    public String addAdmin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String idNumber = request.getParameter("idNumber");
        String password = request.getParameter("password");
        Admin admin = new Admin(username, phone, idNumber, password);
        boolean result = AdminService.insertAdmin(admin);
        Message message = null;
        if (result) {
            message = new Message(0, "成功添加新的快递员");
        } else {
            message = new Message(-1, "快递员信息录入不成功");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/admin/find.do")
    public String find(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        Admin admin = AdminService.findAdminByPhone(phone);
        System.out.println(admin);
        Message message = null;
        if (admin == null) {
            message = new Message(-1, "抱歉，您输入的手机号有误");
        } else {
            message = new Message(0, "快递员信息已经找到",admin);
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/admin/delete.do")
    public String deleteAdmin(HttpServletRequest request, HttpServletResponse response) {
//        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
//        String idNumber = request.getParameter("idNumber");
//        String password = request.getParameter("password");
//        Admin admin = new Admin(username, phone, idNumber, password);
        boolean result = AdminService.deleteAdmin(phone);
        Message message = null;
        if (result) {
            message = new Message(0, "成功删除该快递员资料");
        } else {
            message = new Message(-1, "抱歉，删除不成功");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/admin/update.do")
    public String updateAdmin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String newPhone = request.getParameter("newPhone");
        String oldPhone = request.getParameter("oldPhone");
        String idNumber = request.getParameter("idNumber");
        String password = request.getParameter("password");
        Admin admin = new Admin(username, newPhone, idNumber, password);
        boolean result = AdminService.updateAdmin(oldPhone, admin);
        Message message = null;
        if (result) {
            message = new Message(0, "成功修改该快递员资料");
        } else {
            message = new Message(-1, "抱歉，修改不成功");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/admin/list.do")
    public String getAllAdmin(HttpServletRequest request, HttpServletResponse response) {
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<Admin> adminList = AdminService.findAllAdmin(true, offset, pageNumber);
        List<BootStrapTableAdmin> tableAdminList = new ArrayList<>();
        for (Admin admin: adminList) {
            String id = Integer.toString(admin.getId());
            String idNumber = admin.getIdNumber() != null ? "***" : "无";
            String packageNumber = admin.getPackageNumber() > 100000 ? "10w+" : Integer.toString(admin.getPackageNumber());
            String registerTime = admin.getRegisterTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(admin.getRegisterTime()) : "无";
            String loginTime = admin.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(admin.getLoginTime()) : "无";
            BootStrapTableAdmin tableAdmin = new BootStrapTableAdmin(id, admin.getUsername(), admin.getPhone(), idNumber, admin.getPassword(), packageNumber, registerTime, loginTime);
            tableAdminList.add(tableAdmin);
        }
        int total = AdminService.consoleOfAdmin().get("totalAdmin");
        ResultData resultData = new ResultData();
        resultData.setRows(tableAdminList);
        resultData.setTotal(total);
        String json = JSONUtil.toJSON(resultData);
        return json;
    }
}
