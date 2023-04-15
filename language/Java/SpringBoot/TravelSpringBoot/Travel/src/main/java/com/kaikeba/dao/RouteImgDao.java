package com.kaikeba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaikeba.bean.Category;
import com.kaikeba.bean.RouteImg;
import org.apache.ibatis.annotations.Select;

public interface RouteImgDao extends BaseMapper<RouteImg> {
    @Select("select * from tab_route_img where rid = #{rid}")
    public RouteImg findById(Integer rid);
}
