
create table products(
    id serial ,
    code varchar(255) not null,
    name varchar(255) not null,
    description varchar(255),
    price numeric,
    primary key (id)
);