CREATE SCHEMA `simple_task_db` ;

create table country_codes
(
    id bigint not null auto_increment,
    country_code   varchar(45),
    primary key (id)
);

create table status_codes
(
    id bigint not null auto_increment,
    status_code   varchar(45),
    primary key (id)
);

create table message_types
(
    id bigint not null auto_increment,
    message_type   varchar(45),
    primary key (id)
);

create table message_statuses
(
    id bigint not null auto_increment,
    message_status   varchar(45),
    primary key (id)
);


