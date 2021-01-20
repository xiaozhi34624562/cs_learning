package com.wmz.server_service.service;


import com.wmz.server_service.bean.Bill;
import com.wmz.server_service.dao.BillDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillService {

    @Resource
    private BillDao billDao;

    public List<Bill> findAll(){
        return billDao.selectAll();
    }

    public Bill findById(Long id){
        return billDao.selectByPrimaryKey(id);
    }

//    public List<Bill> findByType(Long type_name){
//        return billDao.selectByType(type_name);
//    }

    public List<Bill> findByTypeAndDate(Bill bill){
        return billDao.selectByTypeAndDate(bill);
    }

    public int add(Bill bill){
        return billDao.insert(bill);
    }

    public int update(Bill bill) {
        return billDao.updateByPrimaryKey(bill);
    }

    public int delete(Long id){
        return billDao.deleteByPrimaryKey(id);
    }



}
