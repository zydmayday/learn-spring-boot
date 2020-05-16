package com.zyd;

import com.zyd.models.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    Triangle triangle;

    @GetMapping("/hello")
    public String hello() {
        triangle.draw();
        return "success";
    }
}
