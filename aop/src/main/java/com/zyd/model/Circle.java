package com.zyd.model;

import com.zyd.aspect.Loggable;
import org.springframework.stereotype.Component;

@Component
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

    @Loggable
    public void setName(String name) {
        this.name = name;
        System.out.println("Circle setName called");
        throw new RuntimeException();
    }

    public String setNameAndReturn(String name) {
        this.name = name;
        System.out.println("Circle setName called");
        return name;
    }
}
