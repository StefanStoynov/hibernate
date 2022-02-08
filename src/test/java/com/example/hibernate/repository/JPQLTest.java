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

    @Test
    void jpdl_courses_with_at_least_2_students() {
        // we are referring to the Entities not the tables behind them
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2 ", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where size(c.students) >= 2 -> {}",resultList);
        //[Course{name='hibernate'}]
    }

    @Test
    void jpdl_courses_order_by_students_asc() {
        // we are referring to the Entities not the tables behind them
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students)", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c order by size(c.students) -> {}",resultList);
        //[Course{name='JPA'}, Course{name='JDBC'}, Course{name='hibernate'}]
    }

    @Test
    void jpdl_courses_order_by_students_desc() {
        //ORDER BY
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c order by size(c.students) desc -> {}",resultList);
        //[Course{name='hibernate'}, Course{name='JDBC'}, Course{name='JPA'}]
    }

    @Test
    void jpdl_students_with_passport_in_a_certain_pattern() {
        //LIKE
        TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%11%' ", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Select s from Student s where s.passport.number like '%11%'  -> {}",resultList);
        //[Student{name='Stefan'}, Student{name='Petar'}, Student{name='Ivan'}]
    }

    //like
    //BETWEEN 100 AND 1000
    //IS NULL
    //FOR STRINGS upper, lower, trim, length

    //JOIN -> Select c, s, from Course c JOIN c students s
    //LEFT JOIN -> Select c, s, from Course c LEFT JOIN c students s; will return courses without students as well
    //CROSS JOIN -> Select c, s from Course c, Student s; if we have 3 Students and 4 Courses will return 12 rows
    @Test
    public void join(){
        // the result contains 2 entities, so it must be a query not a typeQuery or namedQuery
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Result list size  -> {}",resultList.size());
        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
//        Result list size  -> 4
//        Course Course{name='hibernate'}, Student Student{name='Stefan'}
//        Course Course{name='hibernate'}, Student Student{name='Petar'}
//        Course Course{name='hibernate'}, Student Student{name='Ivan'}
//        Course Course{name='JDBC'}, Student Student{name='Stefan'}
    }

    @Test
    public void left_join(){
        // will return courses without students as well
        Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Result list size  -> {}",resultList.size());
        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
//        Result list size  -> 5
//        Course Course{name='hibernate'}, Student Student{name='Stefan'}
//        Course Course{name='hibernate'}, Student Student{name='Petar'}
//        Course Course{name='hibernate'}, Student Student{name='Ivan'}
//        Course Course{name='JPA'}, Student null
//        Course Course{name='JDBC'}, Student Student{name='Stefan'}
    }

    @Test
    public void cross_join(){
        // will return every rows from first table crossed with every row from second table
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Result list size  -> {}",resultList.size());
        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
//        Result list size  -> 9
//        Course Course{name='hibernate'}, Student Student{name='Stefan'}
//        Course Course{name='hibernate'}, Student Student{name='Petar'}
//        Course Course{name='hibernate'}, Student Student{name='Ivan'}
//        Course Course{name='JPA'}, Student Student{name='Stefan'}
//        Course Course{name='JPA'}, Student Student{name='Petar'}
//        Course Course{name='JPA'}, Student Student{name='Ivan'}
//        Course Course{name='JDBC'}, Student Student{name='Stefan'}
//        Course Course{name='JDBC'}, Student Student{name='Petar'}
//        Course Course{name='JDBC'}, Student Student{name='Ivan'}
    }

}