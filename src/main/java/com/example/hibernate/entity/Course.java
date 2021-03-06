package com.example.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name ="course")
//for a SINGLE named query
//@NamedQuery(name = "query_get_all_courses", query = "Select c From Course c")
//for MULTIPLE named queries
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
        @NamedQuery(name = "query_get_all_courses_with_join_fetch", query = "Select c From Course c JOIN FETCH c.students s"),
        @NamedQuery(name = "query_get_courses_where", query = "Select c From Course c Where name like '%100 steps%'")
})
@Cacheable
//when deleting a course @SQLDelete will set is_deleted to true, but course will be in DB
@SQLDelete(sql="update course set is_deleted = true where id = ?")
//will return only objects where is_deleted = false/ don't retrieve inactive courses
@Where(clause="is_deleted=false")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    //@RepositoryRestResource(path="courses") will cause an infinite loop, and we need @JsonIgnore to prevent it
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    private boolean isDeleted;

    @PreRemove
    private void preRemove(){
        this.isDeleted = true;
    }

    //access modifier is important
    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
