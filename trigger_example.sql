create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

insert into products (name, producer, count, price) VALUES ('product_before_1', 'producer_1', 5, 100);
insert into products (name, producer, count, price) VALUES ('product_before_2', 'producer_2', 3, 140);
insert into products (name, producer, count, price) VALUES ('product_before_3', 'producer_3', 12, 35);

-- Функция, увеличивающая цену на величину налога
create or replace function tax_add()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Триггер, запускающий функцию увеличения цены на величину налога после вставки данных
create trigger tax_add_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_add();
	
insert into products (name, producer, count, price) VALUES ('product_after_1', 'producer_1', 7, 100);
insert into products (name, producer, count, price) VALUES ('product_after_2', 'producer_2', 5, 140);
insert into products (name, producer, count, price) VALUES ('product_after_3', 'producer_3', 17, 35);

select * from products;

-- Функция, определяющая цену товара без налога
create or replace function tax_free()
    returns trigger as
$$
    BEGIN        
        NEW.price = price - price * 0.2;            
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

-- Триггер, запускающий функцию определения цены без налога до вставки данных
create trigger tax_free_trigger
    before insert
    on products
    for each row
    execute procedure tax_free();

-- Функция, записывающая цену товара при его добавлении
create or replace function save_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (NEW.name, NEW.price, now());            
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

-- Триггер, запускающий функцию записи цены продуктов
create trigger price_products_trigger
    after insert
    on products
    for each row
    execute procedure save_price();