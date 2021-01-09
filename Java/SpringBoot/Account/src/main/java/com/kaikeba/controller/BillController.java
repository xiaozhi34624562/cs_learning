package com.kaikeba.controller;

import com.kaikeba.bean.Bill;
import com.kaikeba.bean.BillType;
import com.kaikeba.service.BillService;
import com.kaikeba.service.BillTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private BillTypeService billTypeService;

    @RequestMapping("/bill/list")
    public String show (Model model, String typeId, String start, String end) throws ParseException {
        List<BillType> billTypes = billTypeService.getAll();
        model.addAttribute("billTypes", billTypes);
        Bill b = new Bill();
        if (typeId != null ){
            b.setType_id(Integer.parseInt(typeId));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (start != null){
            Date startTime = format.parse(start);
            b.setStart(startTime);
        }
        if (end != null) {
            Date endTime = format.parse(end);
            b.setEnd(endTime);
        }
        List<Bill> bills = billService.findByTypeAndDate(b);
        model.addAttribute("bills", bills);
        model.addAttribute("b", b);
        return "/bill/list";
    }

    @RequestMapping("/bill/add")
    public String add (Model model, String type_id, String title, String bill_time, String price, String explains) {
        List<BillType> types = billTypeService.getAll();
        model.addAttribute("types", types);
        Bill bill = new Bill();
        bill.setTitle(title);
        bill.setExplains(explains);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date billTime = null;
        if (bill_time != null & type_id != null & price != null){
            try {
                billTime = format.parse(bill_time);
                bill.setBill_time(billTime);
                bill.setType_id(Integer.parseInt(type_id));
                bill.setPrice(Double.parseDouble(price));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("添加信息"+bill);
        int add = billService.add(bill);
        System.out.println("添加 + "+add);
        return "/bill/add";
    }

    @RequestMapping("/bill/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println(billService.findById(id));
        int delete = billService.delete(id);
        System.out.println("删除 + "+delete);
        return "redirect:/bill/list";
    }

    @RequestMapping("/bill/toUpdate/{id}")
    public String get(@PathVariable("id") Long id, Model model){
        List<BillType> types = billTypeService.getAll();
        model.addAttribute("types", types);
        Bill bill = billService.findById(id);
        model.addAttribute("billUpdate", bill);
        return "/bill/update";
    }

    @RequestMapping("/bill/update")
    public String update (Model model, String type_id, String title, String bill_time, String price, String explains, HttpServletRequest request) {
        List<BillType> types = billTypeService.getAll();
        model.addAttribute("typeBill", types);
        Bill bill = new Bill();
        bill.setTitle(title);
        bill.setExplains(explains);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date billTime = null;
        if (bill_time != null & type_id != null & price != null){
            try {
                billTime = format.parse(bill_time);
                bill.setBill_time(billTime);
                bill.setType_id(Integer.parseInt(type_id));
                bill.setPrice(Double.parseDouble(price));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        System.out.println("更新信息"+bill);
        int update = billService.update(bill);
        System.out.println("更新信息 + "+update);
        return "redirect:/bill/list";
    }

}
