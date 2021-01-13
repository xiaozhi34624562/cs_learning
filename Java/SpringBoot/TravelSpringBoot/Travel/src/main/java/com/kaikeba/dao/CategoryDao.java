package com.kaikeba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaikeba.bean.Category;
import org.apache.ibatis.annotations.Select;

public interface CategoryDao extends BaseMapper<Category> {
    @Select("select * from tab_category where cid = #{cid}")
    public Category findById(Integer cid);
}
