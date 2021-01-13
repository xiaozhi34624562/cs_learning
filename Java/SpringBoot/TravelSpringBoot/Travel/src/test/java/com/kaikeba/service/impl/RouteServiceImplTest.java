package com.kaikeba.service.impl;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Route;
import com.kaikeba.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {

    @Resource
    private RouteService routeService;

    @Test
    public void page(){
        Route condition = new Route();
        condition.setRname("北");
        PageInfo<Route> page = routeService.findPage(condition, 1, 10);
        page.getList().forEach((r) -> {
            System.out.println(r);
        });
    }

    @Test
    public void info(){
        Route r = routeService.findById(34);
        System.out.println(r.getRid());
    }

}