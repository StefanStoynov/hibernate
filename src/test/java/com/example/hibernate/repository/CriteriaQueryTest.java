package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@SpringBootTest(classes = HibernateApplication.class)
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void jpql() {
        //Steps to create following query
        //"Select c From Course c"

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);
        //3. Define predicates etc using Criteria Builder

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Typed query -> {}",resultList);
        //Typed query -> [Course{name='hibernate'}, Course{name='JPA'}, Course{name='JDBC'}]
    }

}