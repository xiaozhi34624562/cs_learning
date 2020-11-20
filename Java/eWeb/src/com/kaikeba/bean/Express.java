package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Express {
    private int id;
    private String number;
    private String username;
    private String userPhone;
    private String company;
    private String code;
    private Timestamp inTime;
    private Timestamp outTime;
    private int status;
    private String sysPhone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;
        return id == express.id &&
                status == express.status &&
                Objects.equals(number, express.number) &&
                Objects.equals(username, express.username) &&
                Objects.equals(userPhone, express.userPhone) &&
                Objects.equals(company, express.company) &&
                Objects.equals(code, express.code) &&
                Objects.equals(inTime, express.inTime) &&
                Objects.equals(outTime, express.outTime) &&
                Objects.equals(sysPhone, express.sysPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
    }

    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", company='" + company + '\'' +
                ", code='" + code + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", status=" + status +
                ", sysPhone='" + sysPhone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }

    public Express(String number, String username, String userPhone, String company, String sysPhone,String code) {
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.sysPhone = sysPhone;
        this.code = code;
    }

    public Express(String number, String username, String userPhone, String company, String sysPhone) {
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.sysPhone = sysPhone;
    }

    public Express() {
    }

    public Express(int id, String number, String username, String userPhone, String company, String code, Timestamp inTime, Timestamp outTime, int status, String sysPhone) {
        this.id = id;
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.code = code;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.sysPhone = sysPhone;
    }
}
