package com.kaikeba.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("tab_route")
public class Route implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer rid;
    private String rname;
    private Double price;
    private String routeIntroduce;
    private String rflag;
    private String rdate;
    private String isThemeTour;
    private Integer count;
    private Integer cid;
    private String rimage;
    private Integer sid;
    private String sourceId;

    @TableField(exist = false)
    private Category category;
    @TableField(exist = false)
    private Seller seller;
    @TableField(exist = false)
    private List<RouteImg> routeImgList;
}
