package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Course;
import com.mikiruki.javadevrest.models.Student;

import java.util.List;

public interface CourseDAO {

    void save(Course course);
    Course findById(Long id);
    List<Course> list();
    List<Student> getParticipantsByCourseId(Long id);
    List<Course> getUserCourses(int id);
}
