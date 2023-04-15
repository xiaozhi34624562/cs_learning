package com.kaikeba.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Route;
import com.kaikeba.dao.RouteDao;
import com.kaikeba.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RouteServiceImpl implements RouteService {
    @Resource
    private RouteDao routeDao;

    @Override
    public PageInfo<Route> findPage(Route condition, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            routeDao.find(condition);
        });
    }

    @Override
    public int add(Route route) {
        return routeDao.insert(route);
    }

    @Override
    public Route findById(Integer id) {
        return routeDao.findById(id);
    }

    @Override
    public int update(Route route) {
        return routeDao.updateById(route);
    }

    @Override
    public int delete(Integer id) {
        return routeDao.deleteById(id);
    }
}
