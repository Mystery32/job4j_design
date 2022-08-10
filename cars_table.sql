create table car_bodies (
	id serial primary key,
	name varchar(255)
);

create table car_engines (
	id serial primary key,
	name varchar(255)
);

create table car_transmissions (
	id serial primary key,
	name varchar(255)
);

create table cars (
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) 
values ('Седан'), ('Хетчбек'), ('Универсал'), ('Купе');

insert into car_engines(name) 
values ('V4 бензин'), ('V6 бензин'), ('V4 дизель'), ('V6 дизель');

insert into car_transmissions(name) 
values ('Автомат'), ('Робот'), ('Механика');

insert into cars(name, body_id, engine_id, transmission_id) 
values ('BMW', 1, 4, 1), ('Mercedes', 1, 2, 1), ('Toyota', 2, 2, 1), ('Mazda', 3, 1, 3), 
('Lada', 1, 1, 3), ('Audi', 1, 4, 1), ('Волга', 1, null, 3), ('Opel', 3, 4, null);

select c.name Марка_авто, cb.name Тип_кузова, ce.name Тип_двигателя, ct.name Коробка_передач
from cars c
left join car_bodies cb
on c.body_id = cb.id
left join car_engines ce
on c.engine_id = ce.id
left join car_transmissions ct
on c.transmission_id = ct.id;

select cb.name Тип_кузова
from cars c
right join car_bodies cb
on c.body_id = cb.id
where c.body_id is null;

select ce.name Тип_двигателя
from cars c
right join car_engines ce
on c.engine_id = ce.id
where c.engine_id is null;

select ct.name Коробка_передач
from cars c
right join car_transmissions ct
on c.transmission_id = ct.id
where c.transmission_id is null;