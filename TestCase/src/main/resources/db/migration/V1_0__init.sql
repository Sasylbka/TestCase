create table "roles"(
"id" serial primary key,
"name" varchar(64) not null
);

create table "users"(
"id" serial primary key,
"username" varchar(64) not null,
"password" varchar(128) not null,
"role_id" int,
foreign key(role_id) references roles(id)
);

create table "task_priority"(
"id" serial primary key,
"name" varchar(64) not null
);

create table "task_status"(
"id" serial primary key,
"name" varchar(64) not null
);

insert into "roles"("name")
values
('USER');

insert into "task_priority"("name")
values
('LOW'),('MEDIUM'),('HIGH');

insert into "task_status"("name")
values
('AWAITING'),('IN_PROGRESS'),('DONE');

create table "tasks"(
"id" serial primary key,
"title" varchar(64),
"descriprtion" varchar(512),
"task_status_id" int,
"task_priority_id" int,
"author_id" int,
"executor_id" int,
foreign key(author_id) references users(id),
foreign key(executor_id) references users(id),
foreign key(task_status_id) references task_status(id),
foreign key(task_priority_id) references task_priority(id)
);

create table "task_comments"(
"id" serial primary key,
"comment" varchar(512),
"task_id" int,
foreign key(task_id) references tasks(id)
);

create table "users_roles"(
user_id int,
role_id int,
primary key(user_id, role_id),
foreign key(user_id) references users(id),
foreign key(role_id) references roles(id)
);
