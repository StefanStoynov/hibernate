package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Address;
import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Passport;
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
    private StudentRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    //@Transactional creates a Persistence Context where all objects are stored
    //@Transactional all transactions succeed or zero
    @Transactional
    void playTest() {
        //Database Operation 1 - Retrieve student
        Student student = entityManager.find(Student.class, 20002L);
        //Persistence Context (student)

        //Database Operation 2 - Retrieve passport
        Passport passport = student.getPassport();
        //Persistence Context (student, passport)

        //Database Operation 3 - Update passport
        passport.setNumber("N122223");
        //Persistence Context (student, passport++)

        //Database Operation 4 - Update student
        student.setName("Peter Updated");
        //Persistence Context (student++, passport++)

        logger.info("Student -> {}", student);
        logger.info("Student passport -> {}", student.getPassport());
    }


    @Test
    //@Transactional is needed because lazy fetch of passport
    @Transactional
    void saveStudentWithPassport() {
        Student student = entityManager.find(Student.class, 20002L);
        logger.info("Student -> {}", student);
        logger.info("Student passport -> {}", student.getPassport());
    }

    @Test
    //@Transactional is needed because lazy fetch of passport
    @Transactional
    void setAddressDetails() {
        Student student = entityManager.find(Student.class, 20002L);
        student.setAddress(new Address("line1 example", "line2 example", "New York"));
        entityManager.flush();
        logger.info("Student -> {}", student);
    }

    @Test
    //@Transactional is needed because lazy fetch of passport
    @Transactional
    void retrievePassportAndAssociateItWithStudent() {
        Passport passport = entityManager.find(Passport.class, 30001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student associated with the passport -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    void retrieveStudentAndCourses() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Student is associated with the following courses -> {}", student.getCourses());
    }

    @Test
    @Transactional
    void retrieveCourseAndStudents() {
        Course course = entityManager.find(Course.class, 10001L);
        logger.info("Course -> {}", course);
        logger.info("This Course have following students enrolled -> {}", course.getStudents());
    }
}