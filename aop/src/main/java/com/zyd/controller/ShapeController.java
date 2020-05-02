package com.zyd.controller;

import com.zyd.model.Circle;
import com.zyd.model.Triangle;
import com.zyd.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShapeController {

    @Autowired
    ShapeService shapeService;

    @Autowired
    Circle circle;

    @Autowired
    Triangle triangle;

    @RequestMapping("/circle/{name}")
    public Circle getCircle(@PathVariable String name) {
        circle.setName(name);
        return circle;
    }

    @RequestMapping("/triangle/{name}")
    public String getTriangle(@PathVariable String name) {
        triangle.setName(name);
        return triangle.getName();
    }
}
