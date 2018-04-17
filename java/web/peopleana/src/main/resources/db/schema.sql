CREATE TABLE people ( id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, first_name varchar(128) NOT NULL, last_name varchar(128) NOT NULL, is_male boolean NOT NULL, birthday date DEFAULT NULL, tel_no char(32) DEFAULT NULL, email char(128) DEFAULT NULL, mail_addr varchar(256) DEFAULT NULL);

CREATE TABLE marriage ( id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, husband int(11) NOT NULL, wife int(11) NOT NULL, is_divorced boolean NOT NULL, wedding_date date DEFAULT NULL);
