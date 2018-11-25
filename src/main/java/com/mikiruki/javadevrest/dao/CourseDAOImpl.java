package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Course course) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(course);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> list() {
        Session session = this.sessionFactory.openSession();
        return session.createQuery("from Course").list();
    }
}
