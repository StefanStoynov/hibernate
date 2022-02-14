insert into course(id, name, is_deleted) values (10001, 'hibernate', false);
insert into course(id, name, is_deleted) values (10002, 'JPA', false);
insert into course(id, name, is_deleted) values (10003, 'JDBC', false);
-- insert into course(id, name) values (10004, 'Dummy1' );
-- insert into course(id, name) values (10005, 'Dummy2' );
-- insert into course(id, name) values (10006, 'Dummy3' );
-- insert into course(id, name) values (10007, 'Dummy4' );
-- insert into course(id, name) values (10008, 'Dummy5' );

insert into passport(id, number) values (30001,'A11111');
insert into passport(id, number) values (30002,'A11222');
insert into passport(id, number) values (30003,'A11333');

insert into student(id, name, passport_id) values (20001, 'Stefan', 30001);
insert into student(id, name, passport_id) values (20002, 'Petar', 30002);
insert into student(id, name, passport_id) values (20003, 'Ivan', 30003);

insert into review(id, rating, description, course_id) values (40001, 'FIVE', 'Great Course', 10001);
insert into review(id, rating, description, course_id) values (40002, 'FOUR', 'Wonderful Course', 10001);
insert into review(id, rating, description, course_id) values (40003, 'FIVE', 'Awesome Course', 10003);

insert into student_course(student_id, course_id) values (20001, 10001);
insert into student_course(student_id, course_id) values (20002, 10001);
insert into student_course(student_id, course_id) values (20003, 10001);
insert into student_course(student_id, course_id) values (20001, 10003);