create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) 
values ('Иван', 'мужчина'), ('Федор', 'мужчина'), ('Оксана', 'женщина'), ('Виктория', 'женщина'), ('Дарья', 'женщина');

select t1.name, t1.gender, t2.name, t2.gender
from teens as t1
cross join teens as t2
where t1.gender != t2.gender;