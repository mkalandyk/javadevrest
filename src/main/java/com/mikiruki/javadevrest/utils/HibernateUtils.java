package com.mikiruki.javadevrest.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateUtils {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }
}
