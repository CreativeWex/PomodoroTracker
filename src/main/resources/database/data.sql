delete from tasks where id <> -1;
insert into tasks(name, date, description)
values
('Помыть кота', NULL, ''),
('Погладить кота', '2008-11-24', 'кот хочет погладиться');