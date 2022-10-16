
create table inventory(
    id serial ,
    product_code varchar(255) not null,
    quantity numeric,
    primary key (id)
);