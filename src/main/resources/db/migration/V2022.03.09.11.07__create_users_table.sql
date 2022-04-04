create table users
(
    id       bigserial primary key,
    nickname varchar(20) UNIQUE NOT NULL
);