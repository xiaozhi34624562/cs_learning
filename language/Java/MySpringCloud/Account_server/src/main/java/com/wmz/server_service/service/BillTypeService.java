package com.wmz.server_service.service;

import com.wmz.server_service.bean.BillType;
import com.wmz.server_service.dao.BillTypeDao;
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
