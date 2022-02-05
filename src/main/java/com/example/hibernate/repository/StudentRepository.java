package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Passport;
import com.example.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    //findByID(Long id)
    //save(Student Student) -> insert or update
    //deleteByID(Long id)

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student){
        //check if it is insert or update method
        if (student.getId() == null){
            //insert
            em.persist(student);
        }else{
            //update
            em.merge(student);
        }
        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("z12345");
        //passport must be in DB in order to use it with student relationship (after persist hibernate generates Passport ID)
        em.persist(passport);

        Student student = new Student("Georgi");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertStudentAndCourseHardcoded(){
        Student student = new Student("Stamatik");
        Course course = new Course("Course of Stamatik");
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){
        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);
    }




}
