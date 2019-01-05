package com.mikiruki.javadevrest.controllers;

import com.mikiruki.javadevrest.dao.CourseDAO;
import com.mikiruki.javadevrest.models.Course;
import com.mikiruki.javadevrest.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseDAO courseDAO;

    @GetMapping("")
    //public Resources<Course> courses() {
    public List<Course> courses() {

        List<Course> courses = courseDAO.list();

        courses.forEach(c -> {
            c.add(linkTo(CourseController.class).slash(c.getCourseId()).withSelfRel());
            c.getStudents().forEach(s -> {
                if(!s.hasLink("self"))
                    s.add(linkTo(StudentController.class).slash(s.getStudentId()).withSelfRel());
            });
        });

        return courses;
        //return new Resources<>(courses, linkTo(CourseController.class).withSelfRel());
    }

    @GetMapping("/{id}/participants")
    public Resources<Student> participants(@PathVariable Long id) {

        List<Student> participants = courseDAO.getParticipantsByCourseId(id);
        participants.forEach(s -> s.add(linkTo(StudentController.class).slash(s.getStudentId()).withSelfRel()));

        return new Resources<>(participants, linkTo(CourseController.class).slash(id).slash("participants").withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Course> course(@PathVariable Long id) {

        Course course = courseDAO.findById(id);
        course.getStudents().forEach(s -> s.add(linkTo(StudentController.class).slash(s.getStudentId()).withSelfRel()));

        List<Link> links = new ArrayList<>();
        links.add(linkTo(CourseController.class).slash(course.getCourseId()).withSelfRel());
        links.add(linkTo(CourseController.class).slash(course.getCourseId()).slash("participants").withRel("participants"));
        links.add(linkTo(CourseController.class).withRel("allCourses"));

        return new Resource<>(course, links);
    }
}
