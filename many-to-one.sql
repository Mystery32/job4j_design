create table Racer(
	id serial primary key,
	name varchar(255)	
);

create table Car(
	id serial primary key,
	name varchar(255),
	driver_id int references Racer(id)
);

insert into Racer(name) values ('John');
insert into Car(name, driver_id) values ('BMW', 1);

select * from Car;
select * from Racer where id in (select id from Car);