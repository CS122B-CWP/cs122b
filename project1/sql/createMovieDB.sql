/*
	sql script to generate moviedb
*/

SHOW databases;
DROP DATABASE moviedb;
CREATE DATABASE IF NOT EXISTS moviedb;
USE moviedb;

/* create movies	table
moviesididfirst_name
id:integer (primary key)	required, AUTO_INCREMENT
title:varchar(100) 			required 
year:integer 				required starsstarsstars
director:varchar(100) 		required 
banner_url:varchar(200) 	URL of movie's "poster"; not required 
trailer_url:varchar(200) 	URL of trailer; not required
*/

CREATE TABLE IF NOT EXISTS movies 
(id INT(32) AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(100) NOT NULL,
year INT(32) NOT NULL,
dirctor VARCHAR(100) NOT NULL,
banner_url VARCHAR(200) COMMENT 'URL of movie\'s "poster"',
trailer VARCHAR(200) COMMENT 'URL of trailer'
);

DESCRIBE movies;
/*
stars
id:integer (primary key)	required, AUTO_INCREMENT
first_name:varchar(50) 		required 
last_name:varchar(50) 		required 
dob:date 					not required
photo_url:varchar(200) 		not required
*/

CREATE TABLE IF NOT EXISTS stars 
(id INT(32) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
dob	DATE,
photo_url VARCHAR(200)
);
DESCRIBE stars;

/*
stars_in_movies
star_id:integer, referencing stars.id		all attributes required
movie_id:integer, referencing movies.id		all attributes required
Default update or delete in star and movie table won't affact this.
*/
CREATE TABLE IF NOT EXISTS stars_in_movies
(
stars_id INT(32) NOT NULL,
movie_id INT(32) NOT NULL,
FOREIGN KEY (stars_id) REFERENCES stars(id),
FOREIGN KEY (movie_id) REFERENCES movies(id)
);
DESCRIBE stars_in_movies;

/**
genres
id:integer (primary key)	 "id" should be "AUTO_INCREMENT"
name:varchar(32)
all attributes required; 			
*/
CREATE TABLE IF NOT EXISTS genres
(
id INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(32) NOT NULL
);
DESCRIBE genres;

/*genres_in_movies
genre_id:integer, referencing genres.id
movie_id:integer, referencing movies.id
all attributes required
*/
CREATE TABLE IF NOT EXISTS genres_in_movies
(
genre_id INT(32) NOT NULL,
movie_id INT(32) NOT NULL,
FOREIGN KEY (genre_id) REFERENCES genres(id),
FOREIGN KEY (movie_id) REFERENCES movies(id)
);
DESCRIBE genres_in_movies;

/*
creditcards
id:varchar(20), (primary key)
first_name:varchar(50) 
last_name:varchar(50) 
expiration:date 
all attributes required
*/
CREATE TABLE IF NOT EXISTS creditcards
(id VARCHAR(20) NOT NULL PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
expiration	DATE NOT NULL
);
DESCRIBE creditcards;

/*
customers
id:integer (primary key)  "id" should be "AUTO_INCREMENT"
first_name:varchar(50) 
last_name:varchar(50) 
cc_id:varchar(20), referencing creditcards.id
address:varchar(200) 
email:varchar(50) 
password:varchar(20) 
all attributes required;
*/

CREATE TABLE IF NOT EXISTS customers
(id INT(32) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
cc_id	VARCHAR(20) NOT NULL,
address VARCHAR(200) NOT NULL,
email varchar(50) NOT NULL,
password varchar(20) NOT NULL,
FOREIGN KEY (cc_id) REFERENCES creditcards(id)
);
DESCRIBE customers;

/*
sales
id:integer (primary key) "id" should be "AUTO_INCREMENT"
customer_id:integer, referencing customers.id
movie_id:integer, referencing movies.id
sale_date:date 
all attributes required; 

*/
CREATE TABLE IF NOT EXISTS sales
(
id INT(32) AUTO_INCREMENT PRIMARY KEY,
customer_id INT(32) NOT NULL,
movie_id INT(32) NOT NULL,
sale_date DATE NOT NULL,
FOREIGN KEY (movie_id) REFERENCES movies(id),
FOREIGN KEY (customer_id) REFERENCES customers(id)
);
DESCRIBE sales;