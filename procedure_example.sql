create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) values ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) values ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) values ('product_3', 'producer_3', 8, 115);

create or replace procedure delete_data()
language 'plpgsql'
as $$
    BEGIN
        delete from products where count < 5;        
    END;
$$;

call delete_data();

create or replace function f_delete_data()
returns integer
language 'plpgsql'
as
$$
	declare
        result integer;
    begin
        select into result count(*) from products where id  > 1;
        delete from products where id > 1;
        return result;
    end;
$$;

select f_delete_data();