package com.mikiruki.javadevrest.dao;

import com.mikiruki.javadevrest.models.Credentials;
import com.mikiruki.javadevrest.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try(Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> list() {
        Session session = this.sessionFactory.openSession();
        List<Student> allStudents = session.createQuery("from Student").list();
        session.close();
        return allStudents;
    }

    @Override
    public Student getStudentById(int id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Student where id = :id");
        query.setParameter("id", id);
        Student student = (Student) query.uniqueResult();
        session.close();
        return student;
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Student where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception exc) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public Student login(Credentials credentials) {

        int albumNr = 0;
        try {
            albumNr = Integer.parseInt(credentials.getLogin());
        } catch (NumberFormatException exc) {
        }

        Session session = this.sessionFactory.openSession();
        Query query;

        if(albumNr == 0) {
            query = session.createQuery("from Student where email = :email and password = :pass");
            query.setParameter("email", credentials.getLogin());
        } else {
            query = session.createQuery("from Student where albumNumber = :albumNr and password = :pass");
            query.setParameter("albumNr", albumNr);
        }

        query.setParameter("pass", credentials.getPassword());
        Student student = (Student) query.uniqueResult();
        session.close();

        return student;
    }
}
