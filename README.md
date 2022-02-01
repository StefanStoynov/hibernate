# hibernate

@DirtiesContext annotation in test class is used to repair the data after the test is done

#Entity manager methods: 
    see CourseRepository.java
    - .flush() sends changes to DB
    - .detach(object) - changes to the object are no longer tracked by entity manager(specific object)
    - .clear() deleting all tracking by entity manager(all objects are no longer tracked)
    - .refresh(object) - takes the object from DB (last .flash()) and remuves all the changes (refreshing the object to the last commited change form the DB)