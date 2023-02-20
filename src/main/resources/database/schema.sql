drop table if exists cards cascade;

create table cards
(
    id bigint primary key generated always as identity,
    question text not null unique,
    answer text not null,
    difficulty varchar not null default('MEDIUM'),
    category text not null
);