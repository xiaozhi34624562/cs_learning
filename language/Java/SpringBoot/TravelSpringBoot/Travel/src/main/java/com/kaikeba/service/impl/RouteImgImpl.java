package com.kaikeba.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaikeba.bean.RouteImg;
import com.kaikeba.dao.RouteImgDao;
import com.kaikeba.service.RouteImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RouteImgImpl implements RouteImgService {
    @Resource
    private RouteImgDao routeImgDao;

    @Override
    public void saveImg(Integer rid, List<RouteImg> routeImgs) {
        routeImgDao.delete(Wrappers.<RouteImg>query().eq("rid", rid));
        for (RouteImg r : routeImgs) {
            routeImgDao.insert(r);
        }
    }
}
