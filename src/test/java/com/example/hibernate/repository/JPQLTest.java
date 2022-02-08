package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = HibernateApplication.class)
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void jpql() {
        List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
        logger.info("Select c From Course c -> {}",resultList);
    }

    @Test
    void jpql_typed() {
                                                    //query, what type we expect as a result
        TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}",resultList);
    }

    @Test
    void jpql_where() {
        //query, what type we expect as a result
        TypedQuery<Course> query = em.createNamedQuery("query_get_courses_where", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c Where name like '%100 steps%'-> {}",resultList);
        //[Course{name='Web in 100 steps updated'}, Course{name='Java 100 steps'}]
    }

    @Test
    void jpdl_courses_without_students() {
        // we are referring to the Entities not the tables behind them
        TypedQuery<Course> query = em.createQuery("Select c from Course c where  c.students is empty ", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where  c.students is empty -> {}",resultList);
        //[Course{name='JPA'}]
    }

}