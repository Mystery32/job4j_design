create table gender (
    id serial primary key,
    name varchar(10)
);

insert into gender (name) values ('Мужчина');
insert into gender (name) values ('Женщина');

create table block (
    id serial primary key,
    name varchar(30)
);

insert into block (name) values ('Административный');
insert into block (name) values ('Производственный');
insert into block (name) values ('Снабжения');

create table department (
    id serial primary key,
    name varchar(50),
    block_id integer references block(id)
);

insert into department (name, block_id) values ('Экономический', 1);
insert into department (name, block_id) values ('Юридический', 1);
insert into department (name, block_id) values ('Отдел кадров', 1);
insert into department (name, block_id) values ('Ремонт кранов', 2);
insert into department (name, block_id) values ('Производство кранов', 2);
insert into department (name, block_id) values ('Производство запчастей', 2);
insert into department (name, block_id) values ('Обеспечание рудой', 3);
insert into department (name, block_id) values ('Обеспечание станками', 3);
insert into department (name, block_id) values ('Обеспечание канцелярией', 3);

create table employees (
    id serial primary key,
	name varchar(50),
    gender_id integer references gender(id),
    department_id integer references department(id)
);

insert into employees (name, gender_id, department_id) values ('Андрей', 1, 2);
insert into employees (name, gender_id, department_id) values ('Оксана', 2, 2);
insert into employees (name, gender_id, department_id) values ('Елена', 2, 1);
insert into employees (name, gender_id, department_id) values ('Вероника', 2, 1);
insert into employees (name, gender_id, department_id) values ('Татьяна', 2, 1);
insert into employees (name, gender_id, department_id) values ('Ольга', 2, 1);
insert into employees (name, gender_id, department_id) values ('Галина', 2, 3);
insert into employees (name, gender_id, department_id) values ('Евгения', 2, 3);
insert into employees (name, gender_id, department_id) values ('Мария', 2, 3);
insert into employees (name, gender_id, department_id) values ('Григорий', 1, 4);
insert into employees (name, gender_id, department_id) values ('Василий', 1, 4);
insert into employees (name, gender_id, department_id) values ('Михаил', 1, 5);
insert into employees (name, gender_id, department_id) values ('Виктор', 1, 5);
insert into employees (name, gender_id, department_id) values ('Ирина', 2, 5);
insert into employees (name, gender_id, department_id) values ('Светлана', 2, 6);
insert into employees (name, gender_id, department_id) values ('Артем', 1, 6);
insert into employees (name, gender_id, department_id) values ('Марина', 2, 7);
insert into employees (name, gender_id, department_id) values ('Диана', 2, 7);
insert into employees (name, gender_id, department_id) values ('Егор', 1, 7);
insert into employees (name, gender_id, department_id) values ('Денис', 1, 8);
insert into employees (name, gender_id, department_id) values ('Маргарита', 2, 8);
insert into employees (name, gender_id, department_id) values ('Людмила', 2, 9);
insert into employees (name, gender_id, department_id) values ('Анастасия', 2, 9);

create view show_departments_with_3_or_more_womens
    as select g.name Пол_работника, count(g.name), d.name Департамент from gender as g
    	 join employees e on g.id = e.gender_id
    	 join department d on e.department_id = d.id
    	 join block b on d.block_id = b.id
    	 group by (d.name, g.name) having count(g.name) >= 3 AND g.name = 'Женщина';

select * from show_departments_with_3_or_more_womens;