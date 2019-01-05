package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Course;
import com.mikiruki.javadevrest.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Course course) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(course);
        transaction.commit();
        session.close();
    }

    @Override
    public Course findById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Course where id = :id");
        query.setParameter("id", id);
        Course course = (Course)query.getSingleResult();
        session.close();
        return course;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> list() {
        Session session = this.sessionFactory.openSession();
        List<Course> allCourses = session.createQuery("from Course").list();
        session.close();
        return allCourses;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getParticipantsByCourseId(Long id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("select s from Student s inner join s.courses c where c.courseId = :id");
        query.setParameter("id", id);
        List<Student> courseParticipants = query.list();
        session.close();
        return courseParticipants;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> getUserCourses(int id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("select c from Course c inner join c.students s where s.studentId = :id");
        query.setParameter("id", id);
        List<Course> studentsCourses = query.list();
        session.close();
        return studentsCourses;
    }
}
