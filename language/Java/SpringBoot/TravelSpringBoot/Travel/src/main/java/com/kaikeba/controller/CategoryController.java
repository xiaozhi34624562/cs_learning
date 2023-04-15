package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Category;
import com.kaikeba.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String all(Model model, Category category,
                      @RequestParam(defaultValue = "1") int pageNum,
                      @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Category> categories = categoryService.categoryPage(category, pageNum, pageSize);
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "/category/add";
    }

    @RequestMapping("/doadd")
    public String doAdd(Category category) {
        int add = categoryService.categoryAdd(category);
        System.out.println("种类增加 + "+add);

        return "redirect:/admin/category/list";
    }

    @RequestMapping("/toupdate/{cid}")
    public String toUpdate(@PathVariable("cid") int cid, Model model) {
        Category findById = categoryService.categoryFindById(cid);
        System.out.println("更新种类之前找到 + " + findById);
        model.addAttribute("category", findById);
        return "category/update";
    }

    @RequestMapping("/doupdate")
    public String doUpdate(Category category) {
        int update = categoryService.categoryUpdate(category);
        System.out.println("更新种类 + "+update);
        return "redirect:/admin/category/list";
    }

    @RequestMapping("/delete/{cid}")
    public String delete(@PathVariable("cid") int cid) {
        int delete = categoryService.categoryDeleteById(cid);
        System.out.println("删除种类 + " + delete);
        return "redirect:/admin/category/list";
    }
}
