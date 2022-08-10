create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('swordfish', 3650, date '1537-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('elephant', 18250, date '1022-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('sparrow', 1460, date '1952-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('turtle', 32850, date '865-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('python', 10950, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age >= 10000 and avg_age <= 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950';