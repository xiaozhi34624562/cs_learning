package com.kaikeba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaikeba.bean.Category;
import com.kaikeba.bean.Seller;
import org.apache.ibatis.annotations.Select;

public interface SellerDao extends BaseMapper<Seller> {
    @Select("select * from tab_seller where sid = #{sid}")
    public Seller findById(Integer sid);
}
