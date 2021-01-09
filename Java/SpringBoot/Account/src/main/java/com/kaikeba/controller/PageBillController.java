package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Bill;
import com.kaikeba.bean.BillType;
import com.kaikeba.service.BillService;
import com.kaikeba.service.BillTypeService;
import com.kaikeba.service.PageBillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PageBillController {
    @Resource
    private BillService billService;
    @Resource
    private BillTypeService billTypeService;
    @Resource
    private PageBillService pageBillService;

    @RequestMapping("/bill/list-page")
    public String pageList(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           Model model,
                           String type_id, String start, String end) throws ParseException {
        List<BillType> types = billTypeService.getAll();
        model.addAttribute("types", types);
        Bill b = new Bill();
        if (Integer.parseInt(type_id) == -1){
            b.setType_id(null);
        } else {
            b.setType_id(Integer.parseInt(type_id));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (start != null & start != ""){
            Date startTime = format.parse(start);
            b.setStart(startTime);
        }
        if (end != null & end != "") {
            Date endTime = format.parse(end);
            b.setEnd(endTime);
        }
        PageInfo<Bill> pageInfo = pageBillService.pageBill(b, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "/bill/list-page";
    }
}
