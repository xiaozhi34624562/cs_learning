package com.kaikeba.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Seller;
import com.kaikeba.dao.SellerDao;
import com.kaikeba.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    private SellerDao sellerDao;

    @Override
    public PageInfo<Seller> sellerFindPage(Seller condition, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            sellerDao.selectList(new QueryWrapper<Seller>());
        });
    }

    @Override
    public List<Seller> sellerFindAll() {
        return sellerDao.selectList(Wrappers.query());
    }

    @Override
    public int sellerAdd(Seller seller) {
        return sellerDao.insert(seller);
    }

    @Override
    public int sellerDeleteById(int id) {
        return sellerDao.deleteById(id);
    }

    @Override
    public Seller sellerFindByID(int id) {
        return sellerDao.selectById(id);
    }

    @Override
    public int sellerUpdate(Seller seller) {
        return sellerDao.updateById(seller);
    }
}
