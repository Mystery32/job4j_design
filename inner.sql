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
insert into Car(name, driver_id) values ('Mercedes', 1);
insert into Car(name, driver_id) values ('Honda', 1);
insert into Car(name) values ('Lada');
insert into Car(name) values ('Toyota');

select C.name, R.name 
from Car as C join Racer as R on C.driver_id = R.id;

select C.name as Имя_авто, R.name as Имя_гонщика
from Car as C join Racer as R on C.driver_id = R.id;

select C.name as "Имя автомобиля", R.name Имя_гонщика 
from Car C join Racer R on C.driver_id = R.id;