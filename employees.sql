create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) 
values ('Экономический отдел'), ('Юридический отдел'), ('IT'), ('Отдел снабжения'), ('Проектный отдел');

insert into employees(name, departments_id)
values ('Мария', 1), ('Светлана', 1), ('Елена', 1);
insert into employees(name, departments_id)
values ('Андрей', 2), ('Ольга', 2);
insert into employees(name, departments_id)
values ('Илья', 3), ('Федор', 3);
insert into employees(name, departments_id)
values ('Марина', 4);
insert into employees(name)
values ('Артем');

select * from employees e left join departments d on e.departments_id = d.id;

select * from employees e right join departments d on e.departments_id = d.id;

select * from employees e full join departments d on e.departments_id = d.id;

select * from employees e cross join departments d;

select d.name 
from departments d
left join employees e
on e.departments_id = d.id
where e.name is null
group by d.name;

select e.id, e.name, d.name
from employees e
left join departments d
on e.departments_id = d.id;

select e.id, e.name, d.name
from departments d
right join employees e
on d.id = e.departments_id;