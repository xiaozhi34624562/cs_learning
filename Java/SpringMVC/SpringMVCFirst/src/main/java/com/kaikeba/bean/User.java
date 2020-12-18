package com.kaikeba.bean;

public class User {
    private String name;
    private String idnumber;
    private int age;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, String idnumber, int age) {
        this.name = name;
        this.idnumber = idnumber;
        this.age = age;
    }
}
