package com.kaikeba.bean;

import java.sql.Date;

public class BootstrapTableUsers {
    private String id;
    private String username;
    private String phone;
    private String password;
    private String registerTime;
    private String loginTime;
    private String idNumber;

    public BootstrapTableUsers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public BootstrapTableUsers(String id, String username, String phone, String password, String registerTime, String loginTime, String idNumber) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.idNumber = idNumber;
    }

    public BootstrapTableUsers(String id, String username, String phone, String password, String registerTime, String loginTime) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
    }
}
