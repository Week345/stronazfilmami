create database if not exists filmDB;

create user if not exists 'filmuser'@'%' identified by 'Password123';

grant all privileges on filmDB.* to 'filmuser'@'%';

use filmDB;

create table if not exists films (prodYear integer,
    rating decimal(5,2),
    ratingSum integer,
    ratingsNumber integer,
    id int not null auto_increment,
    awards text,
    category varchar(50),
    description text,
    title varchar(80),
    primary key (id)) engine=InnoDB default charset=utf8 default collate utf8_polish_ci;

insert into films(prodYear,awards,category,description,title) values(
    1994,
    'EnergaCamerimage, Amerykańska Gildia Scenarzystów, Amerykański Instytut Filmowy x2, Amerykańskie Stowarzyszenie Operatorów Filmowych x2, Japońska Akademia Filmowa',
    'Dramat',
    'Adaptacja opowiadania Stephena Kinga. Niesłusznie skazany na dożywocie bankier, stara się przetrwać w brutalnym, więziennym świecie.',
    'Skazani na Shawshank'
);