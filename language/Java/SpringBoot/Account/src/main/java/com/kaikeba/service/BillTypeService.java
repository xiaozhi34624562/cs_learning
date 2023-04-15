package com.kaikeba.service;

import com.kaikeba.bean.BillType;
import com.kaikeba.dao.BillTypeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillTypeService {
    @Resource
    private BillTypeDao billTypeDao;

    public List<BillType> getAll(){
        return billTypeDao.selectAll();
    }
}
