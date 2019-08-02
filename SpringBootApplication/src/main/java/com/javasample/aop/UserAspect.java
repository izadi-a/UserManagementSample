package com.javasample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * Aspect for monitoring user's method calls
 *
 * @author Ali Izadi
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Configuration
public class UserAspect {

    /**
     * Executes before calling a method
     *
     * @param joinPoint
     * @since 1.0
     */
    @Before("execution(* com.javasample..*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }
}