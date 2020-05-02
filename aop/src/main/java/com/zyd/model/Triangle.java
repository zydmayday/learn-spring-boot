package com.zyd.model;

import org.springframework.stereotype.Component;

@Component
public class Triangle {

    private String name;

    public Triangle() {
    }

    public Triangle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
