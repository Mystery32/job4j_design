create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Аня'), ('Ваня'), ('Боря');
insert into devices(name, price) values ('Smartphone', 23.4), ('Desktop', 74.6), ('Notebook', 57.2);
insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1), (2, 2);
insert into devices_people(device_id, people_id) values (3, 1), (3, 3);

select avg(price) from devices;

select p.name, avg(dp.price) 
from devices_people as dp 
join people p 
on dp.people_id= p.id 
group by p.name;

select p.name, avg(dp.price) 
from devices_people as dp 
join people p 
on dp.people_id= p.id 
group by p.name;
having avg(dp.price) > 5000;