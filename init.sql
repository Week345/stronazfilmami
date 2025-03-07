create database if not exists filmDB;

create user if not exists 'filmuser'@'%' identified by 'Password123';

grant all privileges on filmDB.* to 'filmuser'@'%';

use filmDB;

create table if not exists films (prodYear integer,
    rating decimal(5,2),
    ratingSum integer,
    ratingsNumber integer,
    id int not null auto_increment,
    awards varchar(255),
    category varchar(50),
    description varchar(255),
    title varchar(80),
    primary key (id)) engine=InnoDB;