package com.kaikeba.controller;

import com.kaikeba.bean.*;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.UsersService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UsersController {

    @ResponseBody("/users/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response){
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<Users> userList = UsersService.findAllUser(true, offset, pageNumber);
        List<BootstrapTableUsers> tableUsersList = new ArrayList<>();
        for (Users user : userList) {
            String id = Integer.toString(user.getId());
            String password = user.getPassword() != null ? "***" : "无";
            String registerTime = user.getRegisterTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(user.getRegisterTime()) : "无";
            String loginTime = user.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(user.getLoginTime()) : "无";
            BootstrapTableUsers tableUsers = new BootstrapTableUsers(id, user.getUsername(), user.getPhone(), password, registerTime, loginTime);
            tableUsersList.add(tableUsers);
        }
        int total = UsersService.consoleOfUser().get("totalUsers");
        ResultData resultData = new ResultData();
        resultData.setRows(tableUsersList);
        resultData.setTotal(total);
        String json = JSONUtil.toJSON(resultData);
        return json;
    }

    @ResponseBody("/users/find.do")
    public String find(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        Users user = UsersService.findUserByPhone(phone);
        Message message = null;
        if (user != null) {
            message = new Message(0, "用户信息已找到", user);
        } else {
            message = new Message(-1, "抱歉，您输入的手机号有误");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/users/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String idNumber = request.getParameter("idNumber");
        String password = request.getParameter("password");
        Users user = new Users(username, phone, idNumber, password);
        boolean result = UsersService.insertUser(user);
        Message message = null;
        if (result) {
            message = new Message(0, "成功添加用户");
        } else {
            message = new Message(-1, "用户信息录入不成功");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/users/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        boolean result = UsersService.deleteUser(phone);
        Message message = null;
        if (result) {
            message = new Message(0, "成功删除该用户资料");
        } else {
            message = new Message(-1, "抱歉，删除不成功");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/users/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String newPhone = request.getParameter("newPhone");
        String oldPhone = request.getParameter("oldPhone");
        String idNumber = request.getParameter("idNumber");
        String password = request.getParameter("password");
        Users user = new Users(username, newPhone, idNumber, password);
        boolean result = UsersService.updateUser(oldPhone, user);
        Message message = null;
        if (result) {
            message = new Message(0, "成功修改该快递员资料");
        } else {
            message = new Message(-1, "抱歉，修改不成功");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

}
