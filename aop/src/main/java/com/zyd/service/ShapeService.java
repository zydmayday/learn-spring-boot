package com.zyd.service;

import com.zyd.model.Circle;
import com.zyd.model.Triangle;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ShapeService {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Circle circle() {
        return new Circle();
    }

    @Bean
    public Triangle triangle() {
        return new Triangle();
    }
}
