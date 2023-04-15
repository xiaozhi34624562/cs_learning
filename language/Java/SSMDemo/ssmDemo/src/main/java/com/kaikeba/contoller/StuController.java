package com.kaikeba.contoller;

import com.kaikeba.bean.Student;
import com.kaikeba.service.StuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("login")
public class StuController {

    @Resource
    private StuService stuService;

    @RequestMapping("/login")
    public String login(String name, String passwd, ModelMap modelMap){
        Map map = new HashMap();
        map.put("name", name);
        map.put("passwd", passwd);
        Student student = stuService.selectByNameAndPasswd(map);
        System.out.println("收到信息");
        if (student != null) {
            System.out.println("登录成功");
            modelMap.addAttribute("login", "login");
            return "redirect:main.jsp";
        } else {
            System.out.println("登录失败");
            return "error";
        }

    }

    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        System.out.println("清楚了所有记录");
        return "logout";
    }

    @RequestMapping("/getById")
    public String getById(String id, Model model){
        Integer num = Integer.parseInt(id);
        Student student = stuService.selectById(num);
        model.addAttribute("getInfoById", student);
        return "show";
    }

    @RequestMapping("/deleteById")
    public String deleteById(String id, Model model){
        Integer num = Integer.parseInt(id);
        int result = stuService.delete(num);
        if (result >= 1) {
            System.out.println("成功删除，" + result);
            model.addAttribute("deleteInfoById", "成功删除");
            return "show";
        }
        return "error";

    }

    @RequestMapping("/updateInfo")
    public String updateInfo(Student student, Model model){
        int result = stuService.update(student);
        if (result >= 1) {
            System.out.println("成功修改信息， " + result);
            model.addAttribute("updateInfoById", student);
            return "show";
        }
        return "error";
    }

    @RequestMapping("/addInfo")
    public String addInfo(Student student, Model model){
        int result = stuService.insert(student);
        if (result >= 1) {
            System.out.println("成功添加信息， " + result);
            model.addAttribute("addInfoById", student);
            return "show";
        }
        return "error";
    }
}
