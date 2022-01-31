package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

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


}
