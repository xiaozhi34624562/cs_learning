package com.wmz.server_service.controller;

import com.alibaba.fastjson.JSON;
import com.wmz.server_service.bean.Bill;
import com.wmz.server_service.bean.BillType;
import com.wmz.server_service.bean.ResultBill;
import com.wmz.server_service.service.BillService;
import com.wmz.server_service.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@RestController
//@Controller
@RefreshScope
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private BillTypeService billTypeService;

    @RequestMapping("/bill/list")
    public String show (Bill b){
        ResultBill resultBill = new ResultBill();
        List<BillType> billTypes = billTypeService.getAll();
        resultBill.setBillTypes(billTypes);
        List<Bill> bills = billService.findByTypeAndDate(b);
        resultBill.setBills(bills);
        String result = JSON.toJSONString(resultBill);
        return result;
    }

    @RequestMapping("/bill/add")
    public String add ( Bill bill) {
        ResultBill resultBill = new ResultBill();
        List<BillType> types = billTypeService.getAll();
        resultBill.setBillTypes(types);
        System.out.println("添加信息"+bill);
        int add = billService.add(bill);
        System.out.println("添加 + "+add);
        return JSON.toJSONString(resultBill);
    }

    @RequestMapping("/bill/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        ResultBill resultBill = new ResultBill();
        System.out.println(billService.findById(id));
        int delete = billService.delete(id);
        System.out.println("删除 + "+delete);
        if (delete > 1) {
            resultBill.setMsg("删除成功");
        }
        return JSON.toJSONString(resultBill);
    }

    @RequestMapping("/bill/toUpdate/{id}")
    public String get(@PathVariable("id") Long id){
        ResultBill resultBill = new ResultBill();
        List<BillType> types = billTypeService.getAll();
        resultBill.setBillTypes(types);
        Bill bill = billService.findById(id);
        resultBill.setBill(bill);
        return JSON.toJSONString(resultBill);
    }

    @RequestMapping("/bill/update")
    public String update (Model model, Bill bill) {
        ResultBill resultBill = new ResultBill();
        List<BillType> types = billTypeService.getAll();
        resultBill.setBillTypes(types);
        System.out.println("更新信息"+bill);
        int update = billService.update(bill);
        if (update > 0) {
            resultBill.setMsg("更新成功");
        }
        return JSON.toJSONString(resultBill);
    }

//    @RequestMapping("/bill/{id}")
//    public Bill queryById(@PathVariable("id") Long id) {
//        return billService.findById(id);
//    }

}
