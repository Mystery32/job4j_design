insert into role(name) values ('administrator');
insert into role(name) values ('programmer');
insert into role(name) values ('support');
insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Anton', 2);
insert into users(name, role_id) values ('Dima', 3);

insert into rules(name) values ('Full access');
insert into rules(name) values ('Data editing');
insert into rules(name) values ('Reading data');
insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (3, 3);

insert into item(name, users_id) values ('Program performance report', 1);
insert into item(name, users_id) values ('Update the program', 2);
insert into item(name, users_id) values ('Make a list of bugs', 3);

insert into comments(name, item_id) values ('Making a presentation', 1);
insert into comments(name, item_id) values ('Making changes for the correct operation of programs', 2);
insert into comments(name, item_id) values ('Formation of a list of bugs from program users', 3);

insert into attachs(name, item_id) values ('presentation.docs', 1);
insert into attachs(name, item_id) values ('update.jar', 2);
insert into attachs(name, item_id) values ('bugs.xlsx', 3);

insert into category(name) values ('monthly');
insert into category(name) values ('everyday life');
insert into category(name) values ('weekly');
insert into item(category_id) values (1);
insert into item(category_id) values (2);
insert into item(category_id) values (3);

insert into state(name) values ('performed');
insert into state(name) values ('in work');
insert into state(name) values ('unfulfilled');
insert into item(state_id) values (1);
insert into item(state_id) values (2);
insert into item(state_id) values (3);