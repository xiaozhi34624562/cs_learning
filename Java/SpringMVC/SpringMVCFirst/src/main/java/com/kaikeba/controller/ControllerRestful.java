package com.kaikeba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"get", "post", "delete", "put"})
public class ControllerRestful {

    @RequestMapping(value = "/TryRestful/{uName}", method = RequestMethod.GET)
    public String getMethod(@PathVariable("uName") String name, ModelMap map){
        System.out.println("get method " + name);
        String method = "get method";
        map.addAttribute("get", method);
        return "success";
    }

    @RequestMapping(value = "/TryRestful/{uname}", method = RequestMethod.POST)
    public String postMethod(@PathVariable("uname") String name, ModelMap map){
        System.out.println("post method" + name);
        String method = "post method";
        map.addAttribute("post", method);
        return "success";
    }

    @RequestMapping(value = "/TryRestful/{uname}", method = RequestMethod.DELETE)
    public String deleteMethod(@PathVariable("uname") String name, ModelMap map){
        System.out.println("delete method" + name);
        String method = "delete method";
        map.addAttribute("delete", method);
        return "success";
    }

    @RequestMapping(value = "/TryRestful/{uname}", method = RequestMethod.PUT)
    public String putMethod(@PathVariable("uname") String name, ModelMap map){
        System.out.println("put method" + name);
        String method = "put method";
        map.addAttribute("put", method);
        return "success";
    }

}
