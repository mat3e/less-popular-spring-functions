create table categories
(
    id            bigint(20) primary key auto_increment,
    code          varchar(3)  not null,
    name          varchar(20) not null,
    description   varchar,
    image_url     varchar,
    is_deprecated boolean     not null default false,
    constraint unique_code unique (code)
);
