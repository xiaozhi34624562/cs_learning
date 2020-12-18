package com.kaikeba.bean;

public class Student {
    private int id;
    private String name;
    private int stuAge;
    private String stuNumber;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stuAge=" + stuAge +
                ", stuNumber='" + stuNumber + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public Student() {
    }

    public Student(String name, int stuAge, String stuNumber) {
        this.name = name;
        this.stuAge = stuAge;
        this.stuNumber = stuNumber;
    }

    public Student(int id, String name, int stuAge, String stuNumber) {
        this.id = id;
        this.name = name;
        this.stuAge = stuAge;
        this.stuNumber = stuNumber;
    }
}
