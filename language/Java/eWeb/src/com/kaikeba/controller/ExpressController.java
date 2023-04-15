package com.kaikeba.controller;

import com.kaikeba.bean.BootStrapTableExpress;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.ExpressService;
import com.kaikeba.service.UsersService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressController {

    @ResponseBody("/express/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, Integer>> data = ExpressService.console();
        Map<String, Integer> adminData = AdminService.consoleOfAdmin();
        Map<String, Integer> usersData = UsersService.consoleOfUser();
        data.add(adminData);
        data.add(usersData);
        Message msg = new Message();
        if(data.size() == 0){
            msg.setStatus(-1);
        }else {
            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    @ResponseBody("/express/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response){
        //1.    获取查询数据的起始索引值
        int offset = Integer.parseInt(request.getParameter("offset"));
        //2.    获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //3.    进行查询
        List<Express> list = ExpressService.findAll(true, offset, pageNumber);
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserPhone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        List<Map<String, Integer>> console = ExpressService.console();
        Integer total = console.get(0).get("data1_size");
        //4.    将集合封装为 bootstrap-table识别的格式
        ResultData<BootStrapTableExpress> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        String json = JSONUtil.toJSON(data);
        return json;
    }

    @ResponseBody("/express/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response){
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        //Express e = new Express(number,company,username,userPhone, UserUtil.getUserPhone(request.getSession()));
        Express e = new Express(number,username,userPhone,company,UserUtil.getUserPhone(request.getSession()));
        boolean flag = ExpressService.insert(e);
        Message msg = new Message();
        if(flag){
            //录入成功
            msg.setStatus(0);
            msg.setResult("快递录入成功!");
        }else{
            //录入失败
            msg.setStatus(-1);
            msg.setResult("快递录入失败!");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/express/find.do")
    public String find(HttpServletRequest request,HttpServletResponse response){
        String number = request.getParameter("number");
        Express e = ExpressService.findByNumber(number);
        Message msg = new Message();
        if(e == null){
            msg.setStatus(-1);
            msg.setResult("单号不存在");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/express/update.do")
    public String update(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        int status = Integer.parseInt(request.getParameter("status"));
        Express newExpress = new Express();
        newExpress.setNumber(number);
        newExpress.setCompany(company);
        newExpress.setUsername(username);
        newExpress.setUserPhone(userPhone);
        newExpress.setStatus(status);
        boolean flag = ExpressService.update(id, newExpress);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("修改成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/express/delete.do")
    public String delete(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = ExpressService.delete(id);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("删除成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
