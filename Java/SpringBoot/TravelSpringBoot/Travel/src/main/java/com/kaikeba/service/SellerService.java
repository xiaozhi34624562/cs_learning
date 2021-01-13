package com.kaikeba.service;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Seller;

import java.util.List;

public interface SellerService {

    public PageInfo<Seller> sellerFindPage(Seller condition, int pageNum, int pageSize);

    public List<Seller> sellerFindAll();

    public int sellerAdd(Seller seller);

    public int sellerDeleteById(int id);

    public Seller sellerFindByID(int id);

    public int sellerUpdate(Seller seller);
}
