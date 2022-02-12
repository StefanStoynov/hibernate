package com.example.hibernate.repository;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.entity.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = HibernateApplication.class)
class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent() {
        // find by id will return Optional object
        Optional<Course> courseOptional = repository.findById(10001L);
        Assertions.assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(10007L);
        Assertions.assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository() {
        Course course = new Course("Java Web");
        //create a new course into DB
        repository.save(course);
        course.setName("Java Web updated");
        //update the course. We use same save method
        repository.save(course);
        //find all courses
        logger.info("Courses: {}", repository.findAll());
        logger.info("Courses count is: {}", repository.count());
    }

    @Test
    public void sort() {
        //sort by name desc
        logger.info("Courses sorted by name desc: {}", repository.findAll(Sort.by(Sort.Direction.DESC, "name")));
        //sort by name asc
        logger.info("Courses sorted by name asc: {}", repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        //sort by name and id. If we have two courses with the same name JPA will sort them by id
        logger.info("Courses sorted by name and id: {}", repository.findAll(Sort.by(Sort.Direction.ASC, "name")
                .and(Sort.by(Sort.Direction.ASC, "id"))));

    }

    @Test
    public void pagination() {
        //divide the result into pages
        PageRequest firstPageable = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(firstPageable);
        //it will return only the first 3 results of the courses
        logger.info("Courses of the first page are: {}", firstPage.getContent());
        //gets the content of first page
        firstPage.getTotalElements();

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Courses of the second page are: {}", secondPage.getContent());
    }

}
