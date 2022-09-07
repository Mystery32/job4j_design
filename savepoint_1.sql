CREATE TABLE cars (
	id serial PRIMARY KEY,
	name VARCHAR(50),
	engine VARCHAR(30),
	speeds integer
);

INSERT INTO cars (name, engine, speeds) values ('BMW', 'V6', 7);
INSERT INTO cars (name, engine, speeds) values ('Audi', 'V4', 6);
INSERT INTO cars (name, engine, speeds) values ('Mercedes', 'V8', 8);

begin transaction;

INSERT INTO cars (name, engine, speeds) values ('Toyota', 'V4', 6);

select * from cars;

savepoint first_savepoint;

delete from cars where speeds = 8;
update cars set speeds = 9 where name = 'BMW';

select * from cars;

rollback to first_savepoint;

select * from cars;

commit transaction;