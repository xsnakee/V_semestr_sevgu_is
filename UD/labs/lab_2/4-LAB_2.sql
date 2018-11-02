/*2x WITH OUT JOIN*/
SELECT (t1.date_of_lesson), t2.course_name FROM lesson t1, discipline_course t2 WHERE t1.course_num = t2.course_num;

/*2x WITH JOIN*/
SELECT t1.date_of_lesson, t2.course_name FROM lesson t1 JOIN discipline_course t2 ON t1.course_num = t2.course_num;

/*3x WITH OUT JOIN*/
SELECT (t1.date_of_lesson), t2.course_name, t3.fio FROM lesson t1, discipline_course t2, teacher t3 WHERE t1.course_num = t2.course_num AND t2.teacher_num = t3.id;

/*3x WITH JOIN*/
SELECT t1.date_of_lesson, t2.course_name, t3.fio FROM lesson t1 JOIN discipline_course t2 ON t1.course_num = t2.course_num JOIN teacher t3 ON t2.teacher_num = t3.id;

/*RECURSIVE */
SELECT distinct t1.course_num, t2.pre_course_num FROM discipline_course t1, discipline_course t2 WHERE t1.pre_course_num = t2.pre_course_num AND t1.course_num <> t2.course_num;

/*QUERY (SUB QUERY AGREGATE FUNC)*/
SELECT t1.course_num FROM lesson t1 WHERE t1.date_of_lesson = (SELECT MIN(t2.date_of_lesson) FROM lesson t2);

/*QUERY(SUB QUERY MULTI VAL)*/
SELECT t1.course_name FROM discipline_course t1 WHERE t1.course_num IN (SELECT t2.course_num FROM lesson t2) AND t1.teacher_num = 2;

/*QUERY(SUB QUERY SINGLE VAL)*/
SELECT t1.teacher_num FROM discipline_course t1 WHERE t1.course_num = (SELECT t2.course_num FROM lesson t2 WHERE t2.date_of_lesson = '2018-09-02' AND t2.class_room_num = 419);

/*QUERY(SUB QUERY CALC VAL)*/
SELECT t1.course_num FROM  lesson t1 WHERE t1.date_of_lesson = (SELECT (MAX(date_of_lesson) - 1) FROM lesson);

/*QUERY(HAVING HAS SUB QUERY )*/
SELECT COUNT(*), t1.course_num FROM  lesson t1 GROUP BY t1.course_num HAVING t1.course_num IN (SELECT t2.course_num FROM discipline_course t2 WHERE teacher_num = 2);

/*QUERY 2x COPIES*/
SELECT t1.course_name, copy.course_name, t1.course_num FROM discipline_course t1, discipline_course copy 
			WHERE t1.teacher_num = copy.teacher_num AND t1.course_num != copy.course_num;

/*QUERY 2x CORRELATION*/
SELECT t1.course_name, t1.course_description FROM discipline_course t1 WHERE '2018-09-02' > (
		SELECT min(t2.date_of_lesson) FROM lesson t2 WHERE t1.course_num = t2.course_num);
/*OR*/
SELECT t1.course_name, t1.course_description FROM discipline_course t1 WHERE 2 > (
		SELECT count(*) FROM lesson t2 WHERE t1.course_num = t2.course_num);
        
/*EXIST*/
SELECT t1.fio FROM student t1 WHERE EXISTS(SELECT * FROM stud_course t2 WHERE t1.id = t2.student_sid);

/*ALL*/
SELECT DISTINCT t1.course_num FROM lesson t1 
		WHERE t1.date_of_lesson <=  ALL (SELECT t2.date_of_lesson FROM lesson t2 WHERE t2.class_room_num = 419);

/*ANY*/
SELECT t1.course_name FROM discipline_course t1 WHERE t1.course_num = ANY(SELECT t2.course_num FROM lesson t2 WHERE date_of_lesson < '2018-09-03');

-- CREATE VIEW count_of_lesson_view AS(
-- 	SELECT date_of_lesson, count(*) FROM lesson GROUP BY date_of_lesson
-- );
-- INSERT INTO lesson VALUE ('2018-09-04', 501,111011, 'п.з.');
-- DELETE FROM lesson WHERE (date_of_lesson = '2018-09-04' AND class_room_num = 501);

DELIMITER ::
CREATE FUNCTION my_func(val INT) RETURNS INT NOT DETERMINISTIC
BEGIN
	DECLARE myVar int default 2;
    RETURN (abs(myVar * val));
END
::
DELIMITER ;
DROP FUNCTION my_func;
SELECT my_func(id) FROM student;

DELIMITER ::
CREATE TRIGGER lock_insert__when_student_try_register_more_5_courses BEFORE INSERT ON `stud_course` 
	FOR EACH ROW
	BEGIN
    DECLARE count_of_student_courses INT DEFAULT 0;
	SELECT COUNT(*) INTO count_of_student_courses FROM stud_course WHERE student_id = new.student_id;
    IF count_of_student_courses >= 5 
		THEN 
			SIGNAL SQLSTATE '2201R'
				SET MESSAGE_TEXT = "Нельзя зарегистрировать больше 5 курсов";
    END IF;

	END;
    ::
DELIMITER ;
DROP TRIGGER lock_insert__when_student_try_register_more_5_courses;


INSERT INTO stud_course VALUE ('4', '111011');
INSERT INTO stud_course VALUE ('4', '111012');
INSERT INTO stud_course VALUE ('4', '111017');
INSERT INTO stud_course VALUE ('4', '111044');
INSERT INTO stud_course VALUE ('4', '111027');
INSERT INTO stud_course VALUE ('4', '111032');
DELETE FROM stud_course WHERE student_id = 4;

CREATE GENERATOR NUM_GENERATOR;

