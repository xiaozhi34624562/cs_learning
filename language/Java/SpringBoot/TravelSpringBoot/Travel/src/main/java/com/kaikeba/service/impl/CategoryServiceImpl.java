package com.kaikeba.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Category;
import com.kaikeba.dao.CategoryDao;
import com.kaikeba.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public PageInfo<Category> categoryPage(Category category, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->{
            categoryDao.selectList(new QueryWrapper<Category>());
        });
    }

    @Override
    public List<Category> categoryFindAll() {
        return categoryDao.selectList(Wrappers.query());
    }

    @Override
    public Category categoryFindById(int id) {
        return categoryDao.selectById(id);
    }

    @Override
    public int categoryAdd(Category category) {
        return categoryDao.insert(category);
    }

    @Override
    public int categoryDeleteById(int id) {
        return categoryDao.deleteById(id);
    }

    @Override
    public int categoryUpdate(Category category) {
        return categoryDao.updateById(category);
    }
}
