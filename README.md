# hibernate

@DirtiesContext annotation in test class is used to repair the data after the test is done

#Entity manager methods: 
    see CourseRepository.java
    - .flush() sends changes to DB
    - .detach(object) - changes to the object are no longer tracked by entity manager(specific object)
    - .clear() deleting all tracking by entity manager(all objects are no longer tracked)
    - .refresh(object) - takes the object from DB (last .flash()) and remuves all the changes (refreshing the object to the last commited change form the DB)

#Persistent context
keeps tracking of changes of the objects.

#JPQL
Java Persistent Query Language
JPQL is querying from Entity's (sql is querying from DB tables)
example:
SQL: SELECT * FROM COURSE
JPQL: Select c From Course c (case-sensitive)
for more examples see JPQLTest.java

#Hibernate annotations
@Table(name="CourseDetails") - it will create a table in DB course_details. It is being used if the table is with different name than entity
@Column(name="fullname") - used on fields if there is a difference between DB column name and entity field
@Column(name="fullname", nullable = false) - column can not be a null. Prevents bad data
@Column(name="fullname", updateble = false)
@Column(name="fullname", unique = false)
@Column(name="fullname", insertable = false) - if we use this annotation the changes for this column won't be sent to DB
@Column(name="fullname", unique = false, length = 255)

@UpdateTimestamp
@CreationTimestamp

@Transactional - when we have this annotation if we have more than one transaction in single method it will 
execute all of them, or it will not execute eny of them. Context is open in the beginning of the method, and it is 
closed at the end of the method. If we don't have @Transactional context is open and after transaction is passed context
is closed. If we have OneToOne relation with lazy fetch if we do not have @Transactional we can not access
the related object. Entity Manager is used to talk with Persistence Context. Entity manager is interface of Persistence
context.

#Native Queries
see NativeQueryTest.java

#Named Queries
see JPQLTest.java and Course.java

#OneToOne relationship
Student and Passport

