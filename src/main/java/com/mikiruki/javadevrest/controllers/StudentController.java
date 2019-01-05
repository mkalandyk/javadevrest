package com.mikiruki.javadevrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikiruki.javadevrest.dao.CourseDAO;
import com.mikiruki.javadevrest.dao.StudentDAO;
import com.mikiruki.javadevrest.models.Course;
import com.mikiruki.javadevrest.models.Credentials;
import com.mikiruki.javadevrest.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @GetMapping(value = "")
    public List<Student> students() {
        List<Student> students = studentDAO.list();
        for(Student student : students) {
            Link selfLink = linkTo(StudentController.class).slash(student.getStudentId()).withSelfRel();
            student.add(selfLink);
        }
        return students;
    }

    @PostMapping(path = "", consumes = "application/json")
    public void addStudent(@RequestBody String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Student newStudent = mapper.readValue(json, Student.class);
        studentDAO.save(newStudent);
    }

    @GetMapping("/{id}")
    public Student student(@PathVariable int id) {
        return studentDAO.getStudentById(id);
    }

    @GetMapping("/{id}/courses")
    public List<Course> myCourses(@PathVariable int id) {
        return courseDAO.getUserCourses(id);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable int id) {
        studentDAO.delete(id);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public Student studentLogin(@RequestBody String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Credentials credentials = mapper.readValue(json, Credentials.class);
        return studentDAO.login(credentials);
    }
}
