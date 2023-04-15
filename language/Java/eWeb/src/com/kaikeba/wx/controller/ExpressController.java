package com.kaikeba.wx.controller;

import com.kaikeba.bean.BootStrapTableExpress;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.Users;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExpressController {
    @ResponseBody("/wx/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response){
        Users wxUser = UserUtil.getWxUser(request.getSession());
        String userPhone = wxUser.getPhone();
        List<Express> list = null;
        if (wxUser.isUser()){
            list = ExpressService.findByUserPhone(userPhone);
        } else if (ExpressService.findBySysPhone(userPhone) != null){
            list = ExpressService.findBySysPhone(userPhone);
        }
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        if (list != null) {
            for (Express e : list) {
                String inTime = DateFormatUtil.format(e.getInTime());
                String outTime = e.getOutTime() == null ? "未出库" : DateFormatUtil.format(e.getOutTime());
                String status = e.getStatus() == 0 ? "待取件" : "已取件";
                String code = e.getCode() == null ? "已取件" : e.getCode();
                BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(), e.getNumber(), e.getUsername(), e.getUserPhone(), e.getCompany(), code, inTime, outTime, status, e.getSysPhone());
                list2.add(e2);
            }
        }
        Message msg = new Message();
        if(list == null){
            msg.setStatus(-1);
            msg.setResult("你好新用户，你还没有快递！");
        }else{
            msg.setStatus(0);
            msg.setResult("你好，这是你的快递！");
            Stream<BootStrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Stream<BootStrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap<>();
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/userExpressList.do")
    public String expressList(HttpServletRequest request,HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        List<Express> list = ExpressService.findByUserPhoneAndStatus(userPhone, 0);
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserPhone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        Message msg = new Message();
        if(list.size() == 0){
            msg.setStatus(-1);
            msg.setResult("未查询到快递");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(list2);
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/addExpressByWx.do")
    public String addExpressByWx(HttpServletRequest request,HttpServletResponse response) {
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String company = request.getParameter("company");
        Express e = new Express(number, name, phone, company, UserUtil.getWxUser(request.getSession()).getPhone());
        boolean result = ExpressService.insert(e);
        Message message = new Message();
        if (result) {
            message.setResult("添加快递成功");
        } else {
            message.setResult("添加快递不成功！");
        }
        String json = JSONUtil.toJSON(message);
        return json;

    }
}
