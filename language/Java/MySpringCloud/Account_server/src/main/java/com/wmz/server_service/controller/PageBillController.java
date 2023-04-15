package com.wmz.server_service.controller;

import com.github.pagehelper.PageInfo;
import com.wmz.server_service.bean.Bill;
import com.wmz.server_service.bean.BillType;
import com.wmz.server_service.service.BillService;
import com.wmz.server_service.service.BillTypeService;
import com.wmz.server_service.service.PageBillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

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
                           @RequestParam(defaultValue = "2") int pageSize,
                           Model model,
                           Bill b) throws ParseException {
        List<BillType> types = billTypeService.getAll();
        model.addAttribute("types", types);
        PageInfo<Bill> pageInfo = pageBillService.pageBill(b, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "/bill/list-page";
//        return pageInfo.getList().stream().map(Object::toString).collect(Collectors.joining(";         "));

    }
}
