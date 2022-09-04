CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'Apple'), (2, 'McDonald''s'), (3, 'Intel'), (4, 'Tesla'), (5, 'Gazprom'), (6, 'Sberbank');

insert into person (id, name, company_id) values (1, 'Иван', 1), (2, 'Фёдор', 1), (3, 'Илья', 1), (4, 'Степан', 1), (5, 'Василий', 1),
                                                 (6, 'Артем', 2), (7, 'Андрей', 2), (8, 'Альбина', 2), (9, 'Алексей', 2),
                                                 (10, 'Марина', 3), (11, 'Роман', 3),
                                                 (12, 'Пётр', 4), (13, 'Константин', 4), (14, 'Алёна', 4),
                                                 (15, 'Дмитрий', 5), (16, 'Михаил', 5), (17, 'Сергей', 5), (18, 'Ирина', 5), (19, 'Ольга', 5),
                                                 (20, 'Мария', 6), (21, 'Оксана', 6), (22, 'Альберт', 6);

select p.name as Сотрудник, c.name as Компания
from person p join company c on p.company_id = c.id where company_id != 5;

SELECT p.company_id, c.name as Компания, count(p.company_id) as Количество_работников
from person p join company c on p.company_id = c.id
group by p.company_id, c.name
having count(*) = (
    select count(company_id) from person
    group by company_id
    order by count(company_id) desc
    limit 1
    );