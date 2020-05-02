package com.zyd.model;

import org.springframework.context.annotation.Bean;

public class Circle {

    private String name;

    public Circle() {
    }

    public void init() {
        System.out.println("init circle");
    }

    public void destroy() {
        System.out.println("destroy circle");
    }

    public void get() {
        System.out.println("get circle");
    }

    public Circle(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Circle get Name");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
