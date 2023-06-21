create table product (
    id int not null auto_increment,
    name varchar(255) not null,
    price int not null,
    quantity int not null,
    description varchar(255) not null,
    primary key (id)
);