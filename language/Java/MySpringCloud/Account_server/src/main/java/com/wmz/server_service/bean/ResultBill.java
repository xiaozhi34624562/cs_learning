package com.wmz.server_service.bean;

import lombok.Data;

import java.util.List;

@Data
public class ResultBill {
    private List<Bill> bills;
    private List<BillType> billTypes;
    private Bill bill;
    private int state;
    private String msg;

}
