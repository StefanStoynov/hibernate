package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest(classes = HibernateApplication.class)
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    //@Transactional is needed because lazy fetch of passport
    @Transactional
    void saveStudentWithPassport() {
        Student student = entityManager.find(Student.class, 20002L);
        logger.info("Student -> {}", student);
        logger.info("Student passport -> {}", student.getPassport());
    }
}