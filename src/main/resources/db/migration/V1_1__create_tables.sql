create table country (
	id INT auto_increment,
	name VARCHAR(50)
);



create table "user" (
	id INT auto_increment,
	login VARCHAR(50),
	password VARCHAR(50)
);



create table hotel (
	id INT auto_increment,
	name VARCHAR(50),
	stars INT,
	website VARCHAR(50),
	latitude VARCHAR(50),
	longitude VARCHAR(50),
	features VARCHAR(50)
);



create table tour (
	id INT auto_increment,
	photo VARCHAR(500),
	date TIMESTAMP,
	duration INT,
	description TEXT,
	cost DOUBLE,
	hotel_id INT,
	country_id INT,
	tour_type VARCHAR(50)
);



create table user_tour (
	user_id INT,
	tour_id INT
);


create table review (
	id INT auto_increment,
	date TIMESTAMP,
	text TEXT,
	user_id INT,
	tour_id INT
);