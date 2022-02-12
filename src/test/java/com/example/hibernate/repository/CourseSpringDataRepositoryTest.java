package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = HibernateApplication.class)
class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent(){
        // find by id will return Optional object
        Optional<Course> courseOptional = repository.findById(10001L);
        Assertions.assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent(){
        Optional<Course> courseOptional = repository.findById(10007L);
        Assertions.assertFalse(courseOptional.isPresent());
    }
}