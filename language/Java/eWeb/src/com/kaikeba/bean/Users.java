package com.kaikeba.bean;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class Users {
    private int id;
    private String username;
    private String phone;
    private String password;
    private Date registerTime;
    private Date loginTime;
    private String idNumber;
    private boolean user;

    public Users(int id, String username, String phone, String password, Date registerTime, Date loginTime, String idNumber, boolean user) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.idNumber = idNumber;
        this.user = user;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Users(String username, String phone, String idNumber, String password) {
        this.username = username;
        this.phone = phone;
        this.idNumber = idNumber;
        this.password = password;
    }

    public Users(String username, String phone, String password, Date registerTime, Date loginTime, String idNumber) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.idNumber = idNumber;
    }

    public Users(int id, String username, String phone, String password, Date registerTime, Date loginTime, String idNumber) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.idNumber = idNumber;
    }

    public Users(int id, String username, String phone, String password, Date registerTime, Date loginTime) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", loginTime=" + loginTime +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }

    public Users() {
    }
}
