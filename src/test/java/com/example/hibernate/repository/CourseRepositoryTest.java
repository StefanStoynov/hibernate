package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Test
    void contextLoads() {
        Course course = repository.findById(10001L);
        assertEquals("hibernate", course.getName());
        logger.info("Test is running");
    }

}