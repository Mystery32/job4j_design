create table cars(
	id serial primary key,
	name varchar(255),
	engine text,
	power integer
);

insert into cars(name, engine, power) values('BMW', 'V6', '249');
insert into cars(name, engine, power) values('Toyota', 'V4', '180');
insert into cars(name, engine, power) values('Lada', 'V4', '105');

update cars set name = 'Merсedes'

delete from cars;