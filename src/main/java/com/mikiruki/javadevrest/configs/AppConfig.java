package com.mikiruki.javadevrest.configs;

import com.mikiruki.javadevrest.dao.CourseDAO;
import com.mikiruki.javadevrest.dao.CourseDAOImpl;
import com.mikiruki.javadevrest.dao.StudentDAO;
import com.mikiruki.javadevrest.dao.StudentDAOImpl;
import com.mikiruki.javadevrest.models.Course;
import com.mikiruki.javadevrest.models.Student;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    BasicDataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
        dataSource.setUsername("miki");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    LocalSessionFactoryBean hibernate5AnnotatedSessionFactory() {

        Properties hibernateProperties = new Properties();

        hibernateProperties.put("org.hibernate.dialect.PostgresPlusDialect", "");
        hibernateProperties.put("hibernate.current_session_context_class", "thread");
        hibernateProperties.put("hibernate.show_sql", false);
        hibernateProperties.put("hibernate.enable_lazy_load_no_trans", true);

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setAnnotatedClasses(Course.class, Student.class);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    @Bean
    StudentDAO studentDAO() {
        return new StudentDAOImpl();
    }

    @Bean
    CourseDAO courseDAO() {
        return new CourseDAOImpl();
    }

}
