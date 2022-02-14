package com.example.hibernate.repository;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Review;
import com.example.hibernate.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void playWithEntityManager(){
        //when we are in @Transactional entity manager keeps tracking of manipulation, and it will persist final object to DB
        logger.info("play with entity manager started");
        Course course1 = new Course("Web in 100 steps");
        em.persist(course1);
        Course course2 = new Course("Java 100 steps");
        em.persist(course2);

        //.flush() sends changes to DB
        em.flush();

        //.detach(object) - changes to the object are no longer tracked by entity manager(specific object)
        em.detach(course2);

        //.clear() deleting all tracking by entity manager(all objects are no longer tracked)
        //em.clear();

        course1.setName("Web in 100 steps updated");
        course2.setName("Java 100 steps updated");
        em.flush();
    }


    public void addHardcodedReviewsForCourse() {
        //get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review(ReviewRating.FIVE, "Great Stuff");
        Review review2 = new Review(ReviewRating.FIVE, "Hatsoff");

        //setting the relationship
        //save the Review into Course
        course.addReview(review1);
        //save the Course into Review
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        // save newly created reviews to db, course is already there
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviewList) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());
        reviewList.forEach(review -> {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        });
    }
}
