package com.zyd.controller;

import com.zyd.aspect.LoggingAspect;
import com.zyd.model.Circle;
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

    @RequestMapping("/circle/{name}")
    public Circle getCircle(@PathVariable String name) {
        circle.setName(name);
        return circle;
    }
}
