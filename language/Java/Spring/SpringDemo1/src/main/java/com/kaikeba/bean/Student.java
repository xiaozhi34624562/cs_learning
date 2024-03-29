package com.kaikeba.bean;

public class Student {
    private String name;
    private String grade;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public Student() {
    }
}
