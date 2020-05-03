package com.zyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// starting point
// 第一次设置依赖的时候，Intellij无法找到下述的annotation
// View > ToolWindows > Maven
// 重新import一遍maven的依赖就行了
@SpringBootApplication
public class ApiApp {

    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class , args);
    }
}
