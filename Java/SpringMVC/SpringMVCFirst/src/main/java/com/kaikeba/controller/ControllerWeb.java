package com.kaikeba.controller;

import com.kaikeba.bean.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("sessionValue")
public class ControllerWeb {

    /**
     * 连接web和springMvc
     *
     * @return
     */
    // 必须使用String类型
    // 如果不指定，则post和get都可以
    @RequestMapping(value = "/try", method = RequestMethod.GET)
    public String Try() {
        System.out.println("连接controller和前端");
//        System.out.println(5/0);
        return "success";
    }

    /**
     * 传递参数使用HttpServletRequest,从前台到后台
     */
    @RequestMapping(value = "/parameters1")
    public String getParametersByGet(HttpServletRequest request) {
        String name = request.getParameter("uname");
        String idnumber = request.getParameter("unumber");
        int age = Integer.parseInt(request.getParameter("uage"));
        User user = new User(name, idnumber, age);
        System.out.println(user);
        return "success";
    }

    /**
     * 传递参数,使用key 名字不同时，使用 @RequestParam(value = "name") String username,
     */
    @RequestMapping(value = "/parameters2")
    public String getParametersByKey(String uname, String unumber, String uage) {
        int age = Integer.parseInt(uage);
        User user = new User(uname, unumber, age);
        System.out.println(user);
        return "success";
    }

//    /**
//     * 传递参数，使用自动配对，名字必须相同，此处的不同，故无法取得值
//     */
//    @RequestMapping(value = "/parameters")
//    public String getParametersByClass(User user){
//        User u = new User(user.getName(), user.getIdnumber(), user.getAge());
//        System.out.println(u);
//        return "success";
//    }

    /**
     * 获取日期,只能自动识别2020/02/02这种形式
     *
     *
     * 获取cookie或者头信息
     */
    @RequestMapping("/date1")
    public String getDate(Date birthday,
                          @CookieValue("JSESSIONID") String jsessionId,
                          @RequestHeader("User-Agent") String agent) {
        System.out.println(birthday);
        System.out.println(jsessionId);
        System.out.println(agent);
//        System.out.println(contentType);
        return "success";
    }

    /**
     * 获取日期特殊方式 @DateTimeFormat(pattern = "yyyy-MM-dd")
     */
    @RequestMapping("/date2")
    public String getDateByMVC(@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday) {
        System.out.println(birthday);
        return "success";
    }

    /**
     * 方法1，HttpServletRequest
     * 返回参数，从后台到前台
     */
    @RequestMapping("/getParameters1")
    public String getParameterFromFront1(HttpServletRequest request) {
        String name = "HttpServletRequest";
        request.setAttribute("method1", name);
        return "success";
    }

    /**
     * 方法2，ModelMap map
     * 返回参数，从后台到前台
     */
    @RequestMapping("/getParameters2")
    public String getParameterFromFront2(HttpServletRequest request, ModelMap map) {
        String name = "ModelMap";
        map.addAttribute("method2", name);
        return "success";
    }

    /**
     * 方法3，Model model
     * 返回参数，从后台到前台
     */
    @RequestMapping("/getParameters3")
    public String getParameterFromFront3(HttpServletRequest request, Model model) {
        String name = "Model";
        model.addAttribute("method3", name);
        return "success";
    }

    /**
     * 方法4，Model model
     * 返回参数，从后台到前台
     */
    @RequestMapping("/getParameters4")
    public ModelAndView getParameterFromFront4() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        String name = "ModelAndView";
        modelAndView.addObject("method4", name);
        return modelAndView;
    }

    @RequestMapping("/sessionAndAlert")
    public void useSessionAndAlert(ModelMap map, HttpServletResponse response) {
        String name = "sessionAttributes";
        map.addAttribute("sessionValue", name);
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('登录成功');location.href='index.jsp'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getuser")
    public @ResponseBody  User getuser(){
        User users = new User();
        users.setName("李四");
        users.setAge(19);
        users.setIdnumber("12312");
        System.out.println("getuser---------->");
        return  users;
    }


}
