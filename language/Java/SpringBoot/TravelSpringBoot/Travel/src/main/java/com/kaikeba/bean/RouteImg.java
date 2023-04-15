package com.kaikeba.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tab_route_img")
public class RouteImg implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer rgid;
    private int rid;
    private String bigpic;
    private String smallpic;
}
