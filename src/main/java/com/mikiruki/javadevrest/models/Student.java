package com.mikiruki.javadevrest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Student")
public class Student extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    int studentId;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "faculty")
    String faculty;

    @Column(name = "yearOfStudy")
    Integer yearOfStudy;

    @Column(name = "albumNumber")
    Integer albumNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    List<Course> courses = new LinkedList<>();

    public Student() {
    }

    public Student(int studentId, String firstName, String lastName, String email, String password, String faculty, Integer yearOfStudy, Integer albumNumber, List<Course> courses) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.albumNumber = albumNumber;
        this.courses = courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYearOfStudy() {
        return Objects.requireNonNullElse(yearOfStudy, 0);
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public int getAlbumNumber() {
        return Objects.requireNonNullElse(albumNumber, 0);
    }

    public void setAlbumNumber(Integer albumNumber) {
        this.albumNumber = albumNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
