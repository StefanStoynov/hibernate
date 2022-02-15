package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Employee;
import com.example.hibernate.entity.FullTimeEmployee;
import com.example.hibernate.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public void insert(Employee employee){
        this.em.persist(employee);
    }

    public List<Employee> retrieveAllEmployees(){
        return this.em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        return this.em.createQuery("select pe from PartTimeEmployee pe", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
        return this.em.createQuery("select fe from FullTimeEmployee fe", FullTimeEmployee.class).getResultList();
    }

    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

}
