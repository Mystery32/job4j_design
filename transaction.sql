CREATE TABLE cars (
id serial PRIMARY KEY,
name VARCHAR(50),
engine VARCHAR(30),
speeds integer
);

INSERT INTO cars (name, engine, speeds) values ('BMW', 'V6', 7);
INSERT INTO cars (name, engine, speeds) values ('Audi', 'V4', 6);
INSERT INTO cars (name, engine, speeds) values ('Mercedes', 'V8', 8);

BEGIN TRANSACTION;
SELECT * FROM cars;

insert into cars (name, engine, speeds) VALUES ('Toyota', 'V4', 5);
delete from cars where speeds = 6;
update cars set speeds = 9 where name = 'BMW';

SELECT * FROM cars;

COMMIT;

BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;

SELECT * FROM cars;

insert into cars (name, engine, speeds) VALUES ('Mazda', 'V4', 6);
delete from cars where speeds = 8;
update cars set speeds = 6 where name = 'Toyota';

DROP TABLE cars;

CREATE TABLE cars (
	id serial PRIMARY KEY,
	name VARCHAR(50),
	engine VARCHAR(30),
	speeds integer
);

INSERT INTO cars (name, engine, speeds) values ('BMW', 'V6', 7);
INSERT INTO cars (name, engine, speeds) values ('Audi', 'V4', 6);
INSERT INTO cars (name, engine, speeds) values ('Mercedes', 'V8', 8);

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

select sum(speeds) from cars;
update cars set speeds = 9 where name = 'BMW';