package com.kaikeba.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;


public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date bill_time;
    private Integer type_id;
    private Double price;
    private String explains;


    //    @Transient
    private BillType billType;
    @Transient
    private Date start;
    @Transient
    private Date end;

    public Bill(String title, Date bill_time, Integer type_id, Double price, String explains) {
        this.title = title;
        this.bill_time = bill_time;
        this.type_id = type_id;
        this.price = price;
        this.explains = explains;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bill_time=" + bill_time +
                ", type_id=" + type_id +
                ", price=" + price +
                ", explains='" + explains + '\'' +
                ", billType=" + billType +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Bill(Long id, String title, Date bill_time, Integer type_id, Double price, String explains, BillType billType, Date start, Date end) {
        this.id = id;
        this.title = title;
        this.bill_time = bill_time;
        this.type_id = type_id;
        this.price = price;
        this.explains = explains;
        this.billType = billType;
        this.start = start;
        this.end = end;
    }

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBill_time() {
        return bill_time;
    }

    public void setBill_time(Date bill_time) {
        this.bill_time = bill_time;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
