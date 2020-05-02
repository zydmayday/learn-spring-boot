package com.zyd.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//    @Before("execution(public String getName())")
//    public void LoggingAspect() {
//        System.out.println("Advice run. Get Method called");
//    }

//    @Before("execution(public String com.zyd.model.Circle.getName())")
//    public void LoggingAspect() {
//        System.out.println("Advice run. Get Method called");
//    }

//    @Before("execution(* com.zyd.model.*.get*())")
//    public void LoggingAspect() {
//        System.out.println("Advice run. Get Method called");
//    }

//    @Before("execution(* com.zyd.model.*.get*())")
//    public void secondAdvice() {
//        System.out.println("Second Advice executed.");
//    }

//    @Before("allGetters()")
//    public void secondAdvice() {
//        System.out.println("Second Advice executed.");
//    }

//    @Before("allGetters() && allCircleMethods()")
//    public void LoggingAspect() {
//        System.out.println("Advice run. Get Method called");
//    }
//
//    @Before("allGetters()")
//    public void secondAdvice() {
//        System.out.println("Second Advice executed.");
//    }

    @Before("allCircleMethods()")
    public void LoggingAspect(JoinPoint joinPoint) {
        System.out.println(joinPoint.toString());
    }

    @Before("allMethodsInModelWithNameArgs(name)")
    public void stringArgsMethods(String name) {
        System.out.println("A method that takes String args called");
        System.out.println(name);
    }

    @Pointcut("execution(* com.zyd.model.*.*(..))")
    public void allMethodsInModel() {}

    @Pointcut("within(com.zyd.model.Circle)")
    public void allCircleMethods() {}

    @Pointcut("allMethodsInModel() && args(name)")
    public void allMethodsInModelWithNameArgs(String name) {}

}
