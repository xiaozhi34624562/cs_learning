package com.wmz.server_service.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wmz.server_service.bean.Bill;
import com.wmz.server_service.dao.BillDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
