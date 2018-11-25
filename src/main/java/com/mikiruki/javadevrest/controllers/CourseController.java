package com.mikiruki.javadevrest.controllers;

import com.mikiruki.javadevrest.dao.CourseDAO;
import com.mikiruki.javadevrest.models.Course;
import com.mikiruki.javadevrest.utils.HibernateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public List<Course> courses() {
        CourseDAO courseDAO = HibernateUtils.getContext().getBean(CourseDAO.class);
        return courseDAO.list();
    }

}
