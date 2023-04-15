package com.kaikeba.controller;

import com.kaikeba.bean.Student;
import com.kaikeba.service.ThymeleafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ThymeleafDemo {

    @Autowired
    private ThymeleafService thymeleafService;

    @RequestMapping("/all")
    public String getInfo(Model model){
        List<Student> all = thymeleafService.getAll();
        model.addAttribute("all", all);
        return "studentInfo";
    }
}
