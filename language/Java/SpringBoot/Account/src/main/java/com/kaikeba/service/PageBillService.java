package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Bill;
import com.kaikeba.dao.BillDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PageBillService {
    @Resource
    private BillDao billDao;
//*******************************************************************************
    public PageInfo<Bill> pageBill(Bill bill, int pageNum, int pageSize) {
        PageInfo<Bill> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            billDao.selectByTypeAndDate(bill);
        });
        return pageInfo;
    }
}
