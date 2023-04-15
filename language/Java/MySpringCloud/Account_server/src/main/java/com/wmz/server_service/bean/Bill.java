package com.wmz.server_service.bean;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Date;

@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date bill_time;
    private Integer type_id;
    private Double price;
    private String explains;


    @Transient
    private String type_name;
    @Transient
    private Date start;
    @Transient
    private Date end;


}
