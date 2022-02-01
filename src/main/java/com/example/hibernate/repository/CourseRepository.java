package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    //findByID(Long id)
    //save(Course course) -> insert or update
    //deleteByID(Long id)

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course){
        //check if it is insert or update method
        if (course.getId() == null){
            //insert
            em.persist(course);
        }else{
            //update
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager(){
        //when we are in @Transactional entity manager keeps tracking of manipulation, and it will persist final object to DB
        logger.info("play with entity manager started");
        Course course1 = new Course("Web in 100 steps");
        em.persist(course1);
        Course course2 = new Course("Java 100 steps");
        em.persist(course2);

        //.flush() sends changes to DB
        em.flush();

        //.detach(object) - changes to the object are no longer tracked by entity manager(specific object)
        em.detach(course2);

        //.clear() deleting all tracking by entity manager(all objects are no longer tracked)
        //em.clear();

        course1.setName("Web in 100 steps updated");
        course2.setName("Java 100 steps updated");
        em.flush();
    }


}
