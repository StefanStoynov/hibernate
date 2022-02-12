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

    @Test
    public void playingAroundWithSpringDataRepository(){
        Course course = new Course("Java Web");
        //create a new course into DB
        repository.save(course);
        course.setName("Java Web updated");
        //update the course. We use same save method
        repository.save(course);
        //find all courses
        logger.info("Courses: {}", repository.findAll());
        logger.info("Courses count is: {}", repository.count());
    }

}