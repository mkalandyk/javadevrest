package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Course;

import java.util.List;

public interface CourseDAO {

    void save(Course course);
    List<Course> list();

}
