package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = HibernateApplication.class)
class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    //this test will call every course in one query
    //and then will call for every course result a query to call his students.
    //courses maid be a 10000, and then we will have a problem.
    void getAllCoursesAndAllStudents() {
        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .getResultList();

        courses.forEach(course -> {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        });
    }

    @Test
    @Transactional
    //we can solve the N+1 problem by using this solution without changing relationship between tables to eager,
    //because if we change the relationship we will call every time the students when we call courses _. performance issue
    void solvingNPlusOneProblem_entityGraph() {
        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");

        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        courses.forEach(course -> {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        });
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_JoinFetch() {
        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses_with_join_fetch", Course.class)
                .getResultList();

        courses.forEach(course -> {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        });
    }



}