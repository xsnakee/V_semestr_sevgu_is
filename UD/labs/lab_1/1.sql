create database ud_lab_db2;

create table ud_lab_db2.teacher(
	id int unsigned auto_increment,
	fio varchar(20) not null,
    PRIMARY KEY(id)
);

create table ud_lab_db2.discipline_course(
	course_num int unsigned,
    teacher_num int unsigned,
    course_name varchar(30) not null,
    course_description varchar(200),
    pre_course_num int unsigned,
    PRIMARY KEY(course_num),
    constraint `teacher_num_constraint` FOREIGN KEY (teacher_num) references teacher(id)
    on delete set null on update cascade,
    constraint `pre_course_constraint` FOREIGN KEY(pre_course_num) references discipline_course(course_num)
    on delete set null  on update cascade
);

create table ud_lab_db2.lesson(
	date_of_lesson date not null,
    class_room_num int(3) unsigned not null,
    course_num int unsigned not null,
    lesson_type varchar(7),
    PRIMARY KEY(date_of_lesson, class_room_num),
    constraint `course_num_constraint` FOREIGN KEY (course_num) references discipline_course(course_num)
    on delete cascade on update cascade
);

create table ud_lab_db2.student(
	id int unsigned not null auto_increment,
    fio varchar(20) not null,
    PRIMARY KEY(id)
);

create table ud_lab_db2.stud_course(
	student_id int unsigned not null, 
    course_num int unsigned not null,
    PRIMARY KEY (student_id, course_num),
    constraint `s_id_constraint` foreign key (student_id) references student(id) on delete cascade on update cascade,
    constraint `couse_num_constraint` foreign key (course_num) references discipline_course(course_num) on delete cascade on update cascade
);