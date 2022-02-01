package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Test
    void findById() {
        Course course = repository.findById(10002L);
        assertEquals("JPA", course.getName());
    }

    @Test
    @DirtiesContext
    //@DirtiesContext is used to repair the data after the test is done
    void deleteById() {
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext
    //@DirtiesContext is used to repair the data after the test is done
    void update() {
        Course course = repository.findById(10002L);
        assertEquals("JPA", course.getName());
        course.setName("updated name");
        repository.save(course);
        Course course1 = repository.findById(10002L);
        assertEquals("updated name", course1.getName());
    }

    @Test
    void playWithEntityManager() {
        repository.playWithEntityManager();
    }
}