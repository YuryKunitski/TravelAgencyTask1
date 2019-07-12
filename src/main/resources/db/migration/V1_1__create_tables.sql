create table country (
	id INT,
	name VARCHAR(50)
);



create table "user" (
	id INT,
	login VARCHAR(50),
	password VARCHAR(50)
);



create table hotel (
	id INT,
	name VARCHAR(50),
	stars INT,
	website VARCHAR(50),
	latitude VARCHAR(50),
	longitude VARCHAR(50),
	features VARCHAR(50)
);



create table tour (
	id INT,
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
	id INT,
	date TIMESTAMP,
	text TEXT,
	user_id INT,
	tour_id INT
);