select * from movies where id in (select movie_id from stars_in_movies where stars_id=490009);

select * from movies where id in (select movie_id from stars_in_movies where stars_id in (select id from stars where first_name like 'Ben' and last_name like '%%'));

insert into stars (first_name,last_name, dob)values ('','test','2000-02-30');
delete from stars where last_name='test';

SELECT * FROM creditcards where first_name like '%%' and last_name = 'liu';

insert into customers (first_name,last_name,cc_id,address,email,password)values ('','test',949,'','','');
delete from customers where id=973024;

select * from customers where cc_id = '941';
