package com.example.hibernate.entity;

import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table(name ="course")
//for a SINGLE named query
//@NamedQuery(name = "query_get_all_courses", query = "Select c From Course c")
//for MULTIPLE named queries
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
        @NamedQuery(name = "query_get_courses_where", query = "Select c From Course c Where name like '%100 steps%'")
})
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

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
