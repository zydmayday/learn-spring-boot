package com.zyd.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

//    @Before("allCircleMethods()")
//    public void LoggingAspect(JoinPoint joinPoint) {
////        System.out.println(joinPoint.toString());
//    }
//
//    //    @After("allMethodsInModelWithNameArgs(name)")
//    @AfterReturning(pointcut = "allMethodsInModelWithNameArgs(name)", returning = "returnString")
//    public void stringArgsMethods(String name, String returnString) {
//        System.out.println("A method that takes String args called, name is: " + name);
//    }
//
//    @AfterThrowing(pointcut = "allMethodsInModelWithNameArgs(name)", throwing = "ex")
//    public void exceptionAdvice(String name, Exception ex) {
//        System.out.println("An exception has been thrown");
//    }

//    @Around("allMethodsInModel()")
//    public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
//        Object ret = null;
//        try {
//            System.out.println("Before advice");
//            ret = proceedingJoinPoint.proceed();
//            System.out.println("After returning");
//        } catch (Throwable throwable) {
//            System.out.println("After Throwing");
//        }
//        System.out.println("After finally");
//        return ret;
//    }

    @Around("@annotation(com.zyd.aspect.Loggable)")
    public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object ret = null;
        try {
            System.out.println("Before advice");
            ret = proceedingJoinPoint.proceed();
            System.out.println("After returning");
        } catch (Throwable throwable) {
            System.out.println("After Throwing");
        }
        System.out.println("After finally");
        return ret;
    }

    @Pointcut("execution(* com.zyd.model.*.*(..))")
    public void allMethodsInModel() {
    }

    @Pointcut("within(com.zyd.model.Circle)")
    public void allCircleMethods() {
    }

    @Pointcut("allMethodsInModel() && args(name)")
    public void allMethodsInModelWithNameArgs(String name) {
    }

}
