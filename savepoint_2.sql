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
savepoint first_savepoint;

INSERT INTO cars (name, engine, speeds) values ('Toyota', 'V4', 6);

savepoint second_savepoint;

delete from cars where speeds = 8;

savepoint third_savepoint;

update cars set speeds = 9 where name = 'BMW';

savepoint fourth_savepoint;

rollback to second_savepoint;

commit transaction;

select * from cars;