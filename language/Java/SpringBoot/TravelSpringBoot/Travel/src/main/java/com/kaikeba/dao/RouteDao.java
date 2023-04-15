package com.kaikeba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaikeba.bean.Route;

import java.util.List;

public interface RouteDao extends BaseMapper<Route> {
    public List<Route> find(Route condition);

    public Route findById(Integer id);
}
