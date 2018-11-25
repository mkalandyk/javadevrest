package com.mikiruki.javadevrest.controllers;

import com.mikiruki.javadevrest.dao.StudentDAO;
import com.mikiruki.javadevrest.models.Student;
import com.mikiruki.javadevrest.utils.HibernateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/students")
    public List<Student> students() {
        StudentDAO studentDAO = HibernateUtils.getContext().getBean(StudentDAO.class);
        return studentDAO.list();
    }

}
