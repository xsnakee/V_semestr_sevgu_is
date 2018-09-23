CREATE DATABASE lab1_test;

CREATE TABLE polet(
	data_poleta date not null,
    reis_id int unsigned not null,
    ecip_id int unsigned,
    free_sit int unsigned,
    b_type varchar(10),
    max_weight int unsigned,
    primary key(data_poleta, reis_id));
    
CREATE TABLE beeplan(
	b_type varchar(10) primary key,
    ecip_amount int unsigned,
    places_amount int unsigned,
    bal_weight int unsigned);
    
ALTER TABLE polet ADD CONstraint temp_constraint FOREIGN KEY (b_type) references beeplan(b_type);

INSERT INTO beeplan VALUES ("Ty-154",5,80,5),("Boing-7",8,110,7);

INSERT INTO polet VALUES (2018-10-01,35,17,12,"Ty-154",3),(2018-10-01,112,15,7,"Boing-7",5),(2018-10-02,47,18,4,"Boing-7",4);
INSERT INTO polet VALUE (2018-10-03,35,17,3,"Ty-154",2);

SELECT DISTINCT data_poleta
FROM polet A
WHERE NOT EXISTS
(SELECT b_type
FROM beeplan
WHERE NOT EXISTS
(SELECT b_type
FROM polet B
WHERE B.b_type = beeplan.b_type and A.data_poleta = B.data_poleta));