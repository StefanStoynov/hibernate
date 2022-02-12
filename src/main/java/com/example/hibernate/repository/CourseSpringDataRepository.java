package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

}
