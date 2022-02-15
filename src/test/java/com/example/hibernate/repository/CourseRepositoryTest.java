package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findById() {
        Course course = repository.findById(10002L);
        assertEquals("JPA", course.getName());
    }

    @Test
    @Transactional
    void findById_first_level_cache() {
        Course course = repository.findById(10002L);
        logger.info("First course retrieved {}", course);
        Course course1 = repository.findById(10002L);
        // the course is retrieved without shutting a query to the DB but @Transactional is a must,
        // they need to be in a single transaction
        logger.info("First course retrieved again {}", course1);
        assertEquals("JPA", course.getName());
        assertEquals("JPA", course1.getName());
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
    @DirtiesContext
    void playWithEntityManager() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    //lazy fetching
    void retrieveReviewsForCourse() {
        Course course = repository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    //@Transactional - we do not need @Transactional because we have eager fetch, and we have Course object into persistent context
    //eager fetching
    void retrieveCourseForReviews() {
        Review review = entityManager.find(Review.class,40001L);
        logger.info("This review is for course {}", review.getCourse().getName());
    }
}