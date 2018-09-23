insert into student (fio) values 
	("Иванов Иван Иванович"),
    ("Петров Петр Петрович"),
    ("Елена Петровна Хренова"),
	("Альбиносов Петр Львович"),
    ("Кузнецов Иван Владимирович"),
    ("Белый Сергей Вадимович"), 
    ("Созонов Фёдор Даниилович"),
	("Селезнёв Константин Игоревич"),
    ("Вероломный Кирилл Викторович"),
    ("Авдеев Михаил Львович");

insert into teacher (fio) values 
	("Овечкин Степан Григорьевич"), 
    ("Уникальный Андрей Константинович"), 
    ("Нехороший Кирилл Викторович"),
	("Закрученный Эльдар Сидович"), 
    ("Бурый Владимир Владимирович"), 
    ("Мурзов Биткоин Алексеевич"), 
    ("Пальчик Людмила Даниловна"),
	("Картавая Анастасия Валерьевна"), 
    ("Крылатый Серафим Михайлович"), 
    ("Богатый Рогат Муратович");

insert into discipline_course values 
	(111011,1, "Высшая математика","ВМ",null),
	(111012, 2, "Информатика", "ИС",null),
    (111017,2, "Алгоритмизация и программирование", "АИП", 111012),
    (111021, 8, "Русский язык", "РЯиСФ", null),
    (111027, 5, "Численные методы", "ЧМ", 111017),
    (111037,3, "Теория принятия решений", "ТПР", 111027),
    (111044, 9, "Параллельные вычисления", "ПВ", 111017),
    (111032, null, "Методы исследования операций", "МИО",111027),
    (111035,1, "Экономическая теория","ВМ",null);
    

insert into lesson values 
	('2018-09-01', 512, 111011, "лекция"),
    ('2018-09-01',507,111012,"л.р."),
	('2018-09-01',506,111017,"п.з."),
    ('2018-09-02', 512, 111035, null),
    ('2018-09-02', 501, 111011, "л.р."),
    ('2018-09-02',419,111027,"л.р."),
	('2018-09-02',510,111044,"п.з."),
    ('2018-09-03', 512, 111035, "п.з"),
    ('2018-09-03', 419, 111011, "п.з"),
    ('2018-09-03', 101, 111012, "п.з"),
    ('2018-09-03', 207, 111017, "п.з"),
    ('2018-09-03', 202, 111021, "п.з"),
    ('2018-09-03', 509, 111027, "п.з"),
    ('2018-09-03', 402, 111032, "п.з"),
    ('2018-09-03', 501, 111037, "п.з"),
    ('2018-09-03', 502, 111044, "п.з");
    
insert into stud_course values
	(1, 111011), 
    (2,111011),
    (3, 111011),
    (4,111011),
	(1, 111012), 
    (2,111012),
    (3, 111012),
    (4,111012),
    (5,111021);

/*Каскадирование данных в таблице student_course*/
DELETE FROM student where id=1;
UPDATE student SET id = 15 WHERE id = 2;

 /*Каскадирование при удалении в таблице lessons*/
DELETE FROM discipline_course WHERE course_num = 111035;

/*Использование простого вычисления*/
SELECT * FROM student WHERE (id > 5);
/*Использование простого вычисления, как параметр агрегатной функции*/
SELECT min(class_room_num) AS `class_room_num` FROM lesson;
/*GROUP BY*/
SELECT fio, count(course_num) FROM discipline_course, teacher WHERE id = teacher_num GROUP BY fio;
/*GROUP BY HAVING*/
SELECT fio, count(course_num) FROM discipline_course, teacher WHERE id = teacher_num GROUP BY fio HAVING count(course_num) >= 2;

/*SELECT+UNION*/
SELECT course_num from stud_course UNION SELECT course_num from lesson WHERE course_num != 111011;
/*SELECT+JOIN*/
SELECT * FROM teacher JOIN discipline_course ON id = teacher_num WHERE teacher_num < 4;

/*PROACTION+DIVISION*/
/*Вывести дату когда проводятся всевозможные дисциплины*/
SELECT distinct date_of_lesson FROM 
	lesson A WHERE NOT EXISTS 
	(SELECT course_num 
    FROM discipline_course
    WHERE NOT EXISTS
    (SELECT course_num 
    FROM lesson B
    WHERE B.course_num = discipline_course.course_num AND 
    A.date_of_lesson = B.date_of_lesson));

/*PROACTION+UNION+AND*/
SELECT course_num FROM discipline_course WHERE course_num = 111035 AND teacher_num != NULL UNION SELECT course_num FROM lesson;

/*JOIN+DIVISION-------------------------------*/
 SELECT date_of_lesson, course_name FROM 
	lesson A JOIN discipline_course C ON A.course_num = C.course_num WHERE NOT EXISTS 
	(SELECT course_num 
    FROM discipline_course
    WHERE NOT EXISTS
    (SELECT course_num 
    FROM lesson B
    WHERE B.course_num = discipline_course.course_num AND 
    A.date_of_lesson = B.date_of_lesson)) ;

/*NOT EXISTS + OR*/
(SELECT DISTINCT course_num FROM stud_course T1 WHERE NOT EXISTS(SELECT course_num FROM lesson T2 WHERE T1.course_num = T2.course_num)
	OR course_num = 111012);

/*Добавление нового столбца с ID преподавателя и добавление ограничений*/
/*
ALTER TABLE discipline_course ADD COLUMN teacher_num INT UNSIGNED AFTER course_num;
ALTER TABLE discipline_course ADD CONSTRAINT `teacher_num_constraint`FOREIGN KEY (teacher_num) REFERENCES 
	teacher(id) ON DELETE SET NULL ON UPDATE CASCADE;
*/
/*Заполнение добавленного столбца*/
/*
UPDATE discipline_course SET teacher_num = (SELECT id FROM teacher WHERE teacher.fio = discipline_course.fio_of_teacher);
*/
/*Удаление столбца*/
/*
ALTER TABLE discipline_course DROP FOREIGN KEY fio_constraint;
ALTER TABLE discipline_course DROP fio_of_teacher;
*/
