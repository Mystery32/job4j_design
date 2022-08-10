create table Vin_number(
	id serial primary key,
	number int	
);

create table Car(
	id serial primary key,
	name varchar(255),
	vin int references Vin_number(id) unique
);

insert into Vin_number(number) values (1234567);
insert into Car(name, vin) values ('BMW', 1);

select * from Car;
select * from Vin_number where id in (select id from Car);