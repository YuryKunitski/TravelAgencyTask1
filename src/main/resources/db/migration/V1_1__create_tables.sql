CREATE TYPE features AS ENUM (
    'swimming_pool',
    'free_wifi',
    'parking',
    'children_area',
    'restaurant'
);

CREATE TYPE tour_type AS ENUM (
    'econom',
    'all_inclusive',
    'only_breakfast'
);

create table hotel (
	id serial NOT NULL UNIQUE,
	name VARCHAR(50) NOT NULL,
	stars INT NOT NULL,
	website VARCHAR(50) NOT NULL,
	latitude VARCHAR(50) NOT NULL,
	longitude VARCHAR(50) NOT NULL,
	features VARCHAR(50) NOT NULL
	CONSTRAINT Hotel_pkey PRIMARY KEY (id)
);

create table "user" (
	id serial NOT NULL UNIQUE,
	login VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	CONSTRAINT User_pkey PRIMARY KEY (id)
);

create table country (
	id serial NOT NULL UNIQUE,
	name VARCHAR(50) NOT NULL,
	CONSTRAINT country_pkey PRIMARY KEY (id)
);

create table review (
	id serial NOT NULL UNIQUE,
	date DATE,
	text TEXT,
	user_id INT,
	tour_id INT,
	CONSTRAINT review_pkey PRIMARY KEY (id)
);

create table tour (
	id serial NOT NULL UNIQUE,
	photo VARCHAR(500) NOT NULL,
	date DATE NOT NULL,
	duration INT NOT NULL,
	description TEXT NOT NULL,
	cost double NOT NULL,
	hotel_id INT NOT NULL,
	country_id INT NOT NULL,
	tour_type VARCHAR(50) NOT NULL,
	CONSTRAINT tour_pkey PRIMARY KEY (id)
);

create table user_tour (
	user_id INT NOT NULL,
	tour_id INT NOT NULL
);

ALTER TABLE review
    ADD CONSTRAINT review_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE;

ALTER TABLE review
        ADD CONSTRAINT review_tour_id_fkey FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE;

ALTER TABLE user_tour
    ADD CONSTRAINT user_tour_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE;

ALTER TABLE tour
    ADD CONSTRAINT tour_country_id_fkey FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE;

ALTER TABLE tour
    ADD CONSTRAINT tour_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES hotel(id) ON DELETE CASCADE;

ALTER TABLE user_tour
    ADD CONSTRAINT user_tour_tour_id_fkey FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE;