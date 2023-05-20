drop table if exists cards cascade;

drop table if exists tasks cascade;
create table tasks
(
    id bigint primary key generated always as identity,
    name text not null,
    date date,
    description text,
    is_active boolean default true not null,
    is_important boolean default false not null
);
