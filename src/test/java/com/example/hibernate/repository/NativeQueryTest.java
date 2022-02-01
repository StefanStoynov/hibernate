package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = HibernateApplication.class)
class NativeQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void native_query() {
        List resultList = em.createNativeQuery("Select * from Course", Course.class).getResultList();
        logger.info("Select * From Course -> {}",resultList);
    }

    @Test
    void native_query_with_parameter() {
        Query query = em.createNativeQuery("Select * from Course where id = ?", Course.class);
                           //position, value
        query.setParameter(1,10001L);
        List resultList = query.getResultList();

        logger.info("Select * From Course where id = ? -> {}",resultList);
        //[Course{name='hibernate'}]
    }


    @Test
    void native_query_with_named_parameter() {
        Query query = em.createNativeQuery("Select * from Course where id = :id", Course.class);
                             //name, value
        query.setParameter("id",10001L);
        List resultList = query.getResultList();

        logger.info("Select * From Course where id = :id -> {}",resultList);
        //[Course{name='hibernate'}]
    }

    @Test
    @Transactional
    void native_query_to_update() {
        Query query = em.createNativeQuery("Update Course set name = 'name'", Course.class);
        int rowsUpdated = query.executeUpdate();
        logger.info("Number of rows updated-> {}",rowsUpdated);
    }


}