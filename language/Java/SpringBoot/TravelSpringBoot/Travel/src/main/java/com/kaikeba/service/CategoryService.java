package com.kaikeba.service;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Category;

import javax.annotation.Resource;
import java.util.List;

public interface CategoryService {
    public PageInfo<Category> categoryPage(Category category, int pageNum, int pageSize);

    public List<Category> categoryFindAll();

    public Category categoryFindById(int id);

    public int categoryAdd(Category category);

    public int categoryDeleteById(int id);

    public int categoryUpdate(Category category);

}
