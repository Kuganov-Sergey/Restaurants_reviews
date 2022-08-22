create table if not exists user
(
    id          int primary key auto_increment,
    name        varchar(20) not null,
    surname        varchar(20) not null,
    lastname        varchar(20) not null,
    email    varchar(255) not null,
    registration_date date not null default now()
);
