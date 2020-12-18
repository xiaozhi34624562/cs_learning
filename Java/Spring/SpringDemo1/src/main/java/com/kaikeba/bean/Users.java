package com.kaikeba.bean;

public class Users {
    private String name;
    private int age;

    public Users() {
        System.out.println("Users() in constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public Users(String name, int age) {
        System.out.println("new Users(name, age)");
        this.name = name;
        this.age = age;
    }
}
