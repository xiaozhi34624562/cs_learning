package com.kaikeba.bean;

import java.sql.Date;

public class Admin {
    private int id;
    private String username;
    private String phone;
    private String idNumber;
    private String password;
    private int packageNumber;
    private Date registerTime;
    private Date loginTime;

    public Admin(String username, String phone, String idNumber, String password) {
        this.username = username;
        this.phone = phone;
        this.idNumber = idNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", password='" + password + '\'' +
                ", packageNumber=" + packageNumber +
                ", registerTime=" + registerTime +
                ", loginTime=" + loginTime +
                '}';
    }

    public Admin(String username, String phone, String idNumber, String password, Date registerTime) {
        this.username = username;
        this.phone = phone;
        this.idNumber = idNumber;
        this.password = password;
        this.registerTime = registerTime;
    }

    public Admin(int id, String username, String phone, String idNumber, String password, int packageNumber, Date registerTime, Date loginTime) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.idNumber = idNumber;
        this.password = password;
        this.packageNumber = packageNumber;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
    }

    public Admin() {
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
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
}
