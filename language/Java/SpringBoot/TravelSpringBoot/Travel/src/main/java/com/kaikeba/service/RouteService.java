package com.kaikeba.service;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Route;

public interface RouteService {
    public PageInfo<Route> findPage(Route condition, int pageNum, int pageSize);

    public int add(Route route);

    public Route findById(Integer id);

    public int update(Route route);

    public int delete(Integer id);

}
