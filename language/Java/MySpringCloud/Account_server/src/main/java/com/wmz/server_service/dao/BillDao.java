package com.wmz.server_service.dao;

import com.wmz.server_service.bean.Bill;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BillDao extends Mapper<Bill> {
//    public List<Bill> selectAll();

//    public List<Bill> selectByType(Long type);

    public List<Bill> selectByTypeAndDate(Bill bill);
}
