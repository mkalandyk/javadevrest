package com.mikiruki.javadevrest.models;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "term")
    Date term;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "presence",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    List<Student> students = new LinkedList<>();

    public Course() {
    }

    public Course(int id, String title, Date term, List<Student> students) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
