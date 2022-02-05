package com.example.hibernate;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Review;
import com.example.hibernate.entity.Student;
import com.example.hibernate.repository.CourseRepository;
import com.example.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Course course = courseRepository.findById(10001L);
//        logger.info("Course 10001 {}", course);
//
//        courseRepository.deleteById(10001L);
//
//        courseRepository.save(new Course("new course created"));
//
//        courseRepository.playWithEntityManager();
//        studentRepository.saveStudentWithPassport();
//
//        courseRepository.addHardcodedReviewsForCourse();
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(new Review("5", "Great Stuff"));
//        reviews.add(new Review("5", "Hatsoff"));
//
//        courseRepository.addReviewsForCourse(10003L,reviews);
//        studentRepository.insertStudentAndCourseHardcoded();
        Student student = new Student("Stamatik");
        Course course = new Course("Course of Stamatik");
        studentRepository.insertStudentAndCourse(student,course);


    }
}
