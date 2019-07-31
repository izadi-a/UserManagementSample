package com.javasample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAspect {
    @Before("execution(* com.javasample..*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName());
    }
}