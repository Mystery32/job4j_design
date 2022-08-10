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
insert into devices(name, price) values ('Smartphone', 3800), ('Desktop', 6300), ('Notebook', 5250);
insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1), (2, 2);
insert into devices_people(device_id, people_id) values (3, 1), (3, 3);

select avg(price) from devices;

select p.name, avg(d.price) 
from devices_people as dp
join devices as d
on dp.device_id= d.id
join people as p
on dp.people_id= p.id
group by p.name;

select p.name, avg(d.price) 
from devices_people as dp
join devices as d
on dp.device_id= d.id
join people as p
on dp.people_id= p.id
group by p.name
having avg(d.price) > 5000;