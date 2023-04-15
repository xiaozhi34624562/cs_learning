package com.kaikeba.bean;

import java.util.Objects;

public class LazyUser {
    private int num;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LazyUser lazyUser = (LazyUser) o;
        return num == lazyUser.num &&
                Objects.equals(name, lazyUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name);
    }

    public LazyUser() {
    }

    public int getNum() {

        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LazyUser(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
