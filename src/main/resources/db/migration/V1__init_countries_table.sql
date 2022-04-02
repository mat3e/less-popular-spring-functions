create table countries
(
    id      char(2) primary key,
    locales varchar not null,
    capital char(3) not null,
    constraint unique_capital unique (capital)
);

insert into countries(id, locales, capital)
values ('pl', 'pl,en,uk,de', 'WAW');

insert into countries(id, locales, capital)
values ('ua', 'pl,en,uk', 'KIE');
