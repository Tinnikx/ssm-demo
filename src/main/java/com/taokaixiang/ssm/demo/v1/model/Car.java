package com.taokaixiang.ssm.demo.v1.model;

import java.util.List;

public class Car {

    private String name;

    private String color;

    private List<String> qwe;

    public List<String> getQwe() {
        return qwe;
    }

    public void setQwe(List<String> qwe) {
        this.qwe = qwe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", qwe=" + qwe +
                '}';
    }
}
