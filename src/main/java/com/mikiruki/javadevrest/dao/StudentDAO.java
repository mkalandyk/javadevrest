package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);
    List<Student> list();

}
