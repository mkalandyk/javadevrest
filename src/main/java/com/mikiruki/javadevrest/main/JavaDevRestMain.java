package com.mikiruki.javadevrest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.mikiruki.javadevrest.controllers")
@SpringBootApplication
public class JavaDevRestMain {
    public static void main(String[] args) {
        SpringApplication.run(JavaDevRestMain.class, args);
    }
}
