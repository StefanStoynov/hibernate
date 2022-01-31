package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;

@Repository
public class CourseRepository {

    @Autowired
    EntityManager em;

    //findByID(Long id)
    //save(Course course) -> insert or update
    //deleteByID(Long id)

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }


}
