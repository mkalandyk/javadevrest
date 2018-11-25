package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> list() {
        Session session = this.sessionFactory.openSession();
        return session.createQuery("from Student").list();
    }
}
