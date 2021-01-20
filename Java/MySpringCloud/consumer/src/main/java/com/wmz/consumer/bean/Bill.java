package com.wmz.consumer.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class Bill {

    private Long id;
    private String title;
    private Date bill_time;
    private Integer type_id;
    private Double price;
    private String explains;


    private String type_name;
    private Date start;
    private Date end;


}
