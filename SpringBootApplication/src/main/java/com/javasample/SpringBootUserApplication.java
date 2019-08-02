package com.javasample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Class that can be used to bootstrap and launch a Spring application from a Java main
 * method.
 *
 * @author Ali Izadi
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringBootUserApplication {

    /**
     * Main entry point of the Spring Boot application.
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootUserApplication.class, args);
    }

}