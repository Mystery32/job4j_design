create table Car(
	id serial primary key,
	name varchar(255)	
);

create table Color(
	id serial primary key,
	name varchar(255)	
);

create table Car_color(
	id serial primary key,
	car_id int references car(id),
	color_id int references color(id)
);

insert into Car(name) values ('BMW');
insert into Car(name) values ('Audi');

insert into Color(name) values ('Black');
insert into Color(name) values ('White');

insert into Car_color(car_id, color_id) values (1, 1);
insert into Car_color(car_id, color_id) values (1, 2);
insert into Car_color(car_id, color_id) values (2, 2);

select * from Car_color;