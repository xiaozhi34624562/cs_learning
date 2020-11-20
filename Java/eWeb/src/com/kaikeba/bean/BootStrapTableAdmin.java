package com.kaikeba.bean;

public class BootStrapTableAdmin {
    private String id;
    private String username;
    private String phone;
    private String idNumber;
    private String password;
    private String packageNumber;
    private String registerTime;
    private String loginTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseername() {
        return username;
    }

    public void setUseername(String useername) {
        this.username = useername;
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
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

    public BootStrapTableAdmin(String id, String username, String phone, String idNumber, String password, String packageNumber, String registerTime, String loginTime) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.idNumber = idNumber;
        this.password = password;
        this.packageNumber = packageNumber;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
    }

    public BootStrapTableAdmin() {
    }
}
