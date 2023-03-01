drop table if exists cards cascade;
create table cards
(
    id bigint primary key generated always as identity,
    question text not null unique,
    answer text not null,
    difficulty varchar not null default 'MEDIUM',
    category text not null
);

drop table if exists tasks cascade;
create table tasks
(
    id bigint primary key generated always as identity,
    name text not null unique,
    date date,
    description text,
    status varchar(10) default 'ACTIVE' not null
);

drop table if exists sub_tasks cascade;
create table sub_tasks
(
    id bigint primary key generated always as identity,
    name text not null unique,
    date date,
    description text
);
