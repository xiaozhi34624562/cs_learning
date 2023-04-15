package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.User;
import com.kaikeba.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/toadd")
    public String userToAdd(Model model){
        return "user/add";
    }

    @RequestMapping("/page")
    public String userPage(Model model, User user,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> pageInfo = userService.findPage(user, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "user/list";
    }

    @RequestMapping("/doadd")
    public String userAdd(User user) {
        int add = userService.add(user);
        System.out.println("添加用户 + " + add);
        return "redirect:/admin/user/page";
    }

    @RequestMapping("/toupdate/{id}")
    public String userToUpdate(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @RequestMapping("/doupdate")
    public String userDoUpdate(User user) {
        int update = userService.update(user);
        System.out.println("修改用户 + " + update);
        return "redirect:/admin/user/page";
    }

    @RequestMapping("/delete/{id}")
    public String userDelete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin/user/page";
    }

    @RequestMapping("/delete")
    public String userBatchDelete(Integer[] ids) {
        for (Integer id: ids) {
            int delete = userService.delete(id);
            System.out.println("批量删除 + "+delete);
        }
        return "redirect:/admin/user/page";
    }

}
