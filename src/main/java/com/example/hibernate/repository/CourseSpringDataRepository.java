package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
//will expose the data into url: http://localhost:8080/courses  not recommended for production
//need a dependency: spring-boot-starter-data-rest
@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName (String name);
    List<Course> countByName (String name);
    List<Course> findByNameAndId (String name, Long id);
    List<Course> countByNameOrderByIdDesc (String name);
    List<Course> deleteByName (String name);

    //JPQL query
    @Query("Select c From Course c Where name like '%100 steps%'")
    List<Course> coursesWith100StepsInName();

    //native query
    @Query(value = "Select * From course c Where c.name like '%100 steps%'", nativeQuery = true)
    List<Course> coursesWith100StepsInNameUsingNativeQuery();

    //named query
    @Query(name = "query_get_courses_where")
    List<Course> coursesWith100StepsInNameUsingNamedQuery();
}
