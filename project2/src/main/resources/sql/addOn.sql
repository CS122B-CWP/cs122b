CREATE TABLE IF NOT EXISTS shopping_cart_items (
customer_id INT(10) not null references customers(id),
movie_id INT(10) not null references movies(id),
movie_title varchar(50),
unit_price double(10,2),
qty int(5),
PRIMARY KEY (customer_id, movie_id)
);

alter table movies add price double(10,2);
alter table sales add qty int(10);
alter table sales add total double(10,2);