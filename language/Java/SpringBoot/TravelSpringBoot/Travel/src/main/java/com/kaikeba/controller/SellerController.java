package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Seller;
import com.kaikeba.service.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/seller")
public class SellerController {
    @Resource
    private SellerService sellerService;

    @RequestMapping("/page")
    public String page(Model model,
                       @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       Seller seller) {
        PageInfo<Seller> pageInfo = sellerService.sellerFindPage(seller, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "seller/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "seller/add";
    }

    @RequestMapping("/doadd")
    public String doAdd(Seller seller) {
        int i = sellerService.sellerAdd(seller);
        System.out.println("销售商添加 + " + 1);
        return "redirect:/admin/seller/page";
    }

    @RequestMapping("/toupdate/{sid}")
    public String toUpdate(@PathVariable("sid") int sid, Model model) {
        Seller seller = sellerService.sellerFindByID(sid);
        System.out.println("修改销售商 " + seller);
        model.addAttribute("seller", seller);
        return "seller/update";
    }

    @RequestMapping("/doupdate")
    public String doUpdate(Seller seller) {
        int update = sellerService.sellerUpdate(seller);
        System.out.println("修改供应商 + " + update);
        return "redirect:/admin/seller/page";
    }

    @RequestMapping("/delete/{sid}")
    public String delete(@PathVariable("sid") int sid) {
        int delete = sellerService.sellerDeleteById(sid);
        System.out.println("删除销售商 + "+delete);
        return "redirect:/admin/seller/page";
    }
}
