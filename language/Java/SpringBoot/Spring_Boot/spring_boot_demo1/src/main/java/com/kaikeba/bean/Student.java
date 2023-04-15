package com.kaikeba.bean;

import javax.persistence.*;
import java.io.Serializable;

// 表名不同时 @Table(name="tableName")
// 字段名不同时 @Column(name="columnName")
// 主键一定要说明 @Id @GeneratedValue)
// 不使用的属性 @Transient
// ******************使用Integer而不是int

@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String passwd;

    @Column(name = "stuAge")
    private Integer stuage;

    @Column(name = "stuNumber")
    private String stunumber;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", stuage=" + stuage +
                ", stunumber='" + stunumber + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getStuage() {
        return stuage;
    }

    public void setStuage(Integer stuage) {
        this.stuage = stuage;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
    }
}
