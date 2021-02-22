 drop table if exists `tasks` CASCADE; 
 drop table if exists `to_do_list` CASCADE; 
 
create table to_do_list (id PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null, primary key (id));
create table tasks (id bigint, PRIMARY KEY AUTO_INCREMENT, colour varchar(255) not null, description varchar(255) not null, name varchar(255) not null, todolist_id bigint, primary key (id));
alter table tasks add constraint FKelo7pxri58fjhk4phjx6hgv6n foreign key (todolist_id) references to_do_list on delete cascade;