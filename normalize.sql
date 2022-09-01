create table rental (
    id serial primary key,
    name varchar(30),
    address varchar(255),
    films varchar(255),
    gender varchar(10)
);

insert into rental (name, address, films, gender)
values ('Ольга Егорова', '1-ый Казанский переулок, д. 14', 'Пираты Карибского моря, Матрица: Революция', 'женский');
insert into rental (name, address, films, gender)
values ('Иванов Сергей', 'ул. Центральная, д. 40, кв. 74', 'Человек, который изменил все; Интерстеллар', 'мужской');
insert into rental (name, address, films, gender)
values ('Иванов Сергей', 'ул. Ленина, д. 7, кв. 130', 'Матрица: Революция', 'мужской');

-- Приводим к первой нормальной форме (1НФ): редактируем ранее созданную таблицу с вынесением каждого фильма
-- в отдельные строки

update rental set films = 'Пираты Карибского моря' where id = 1;
insert into rental (name, address, films, gender)
values ('Ольга Егорова', '1-ый Казанский переулок, д. 14', 'Матрица: Революция', 'женский');
update rental set films = 'Человек, который изменил все' where id = 2;
insert into rental (name, address, films, gender)
values ('Иванов Сергей', 'ул. Центральная, д. 40, кв. 74', 'Интерстеллар', 'мужской');

-- Приводим к второй нормальной форме (2НФ): разбиваем таблицу на две таблицы - таблицу, описывающую всех пользователей
-- проката, и таблицу, описывающую фильмы, взятые этими пользователями

create table users (
                        id serial primary key,
                        name varchar(30),
                        address varchar(255),
                        gender varchar(10)
);

insert into users (name, address, gender)
select name, address, gender from rental;

create table films_rent (
                       id serial primary key,
                       name varchar(30),
                       films varchar(255),
                       users_id int references users(id)
);

insert into films_rent (name, films, users_id)
select name, films, 1 from rental where id = 1;
insert into films_rent (name, films, users_id)
select name, films, 2 from rental where id = 2;
insert into films_rent (name, films, users_id)
select name, films, 3 from rental where id = 3;
insert into films_rent (name, films, users_id)
select name, films, 4 from rental where id = 4;
insert into films_rent (name, films, users_id)
select name, films, 5 from rental where id = 5;