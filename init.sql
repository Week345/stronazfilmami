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
    description text,
    title varchar(80),
    image longblob,
    imageType text,
    primary key (id)) engine=InnoDB default charset=utf8 default collate utf8_polish_ci;

create table if not exists categories (id int not null auto_increment,
    name text,
    primary key (id)) engine=InnoDB default charset=utf8 default collate utf8_polish_ci;

create table if not exists film_category (film_id int not null,
    category_id int not null,
    primary key (film_id, category_id),
    foreign key (film_id) references films(id),
    foreign key (category_id) references categories(id)
);

alter table films modify column awards TEXT;
alter table films modify column description TEXT;
alter table films convert to character set utf8 collate utf8_polish_ci;
alter schema filmDB default character set utf8 default collate utf8_polish_ci;

insert into categories(name) values ('Akcja'),
('Dramat'),
('Komedia'),
('Horror'),
('Sci-Fi'),
('Fantasy'),
('Thriller'),
('Romans'),
('Przygodowy'),
('Familijny'),
('Dokumentalny'),
('Musical'),
('Historyczny'),
('Western'),
('Kryminał');

insert into films(prodYear,awards,description,title) values(
    1994,
    'EnergaCamerimage, Amerykańska Gildia Scenarzystów, Amerykański Instytut Filmowy x2, Amerykańskie Stowarzyszenie Operatorów Filmowych x2, Japońska Akademia Filmowa',
    'Adaptacja opowiadania Stephena Kinga. Niesłusznie skazany na dożywocie bankier, stara się przetrwać w brutalnym, więziennym świecie.',
    'Skazani na Shawshank'
);

insert into film_category values((select id from films where title = 'Skazani na Shawshank'),(select id from categories where name = 'Dramat'));

insert into films(prodYear,awards,description,title) values (
    2014,
    'Saturn x3',
    'Niedaleka przyszłość. Wskutek przemian klimatycznych Ziemia jest systematycznie niszczona przez suszę i głód. Głównym priorytetem stojącej przed obliczem zagłady ludzkości staje się wyprodukowanie wystarczającej ilości pożywienia. Tymczasem były pilot NASA zajmujący się obecnie farmą, Cooper (Matthew McConaughey), wpada na trop dziwnej anomalii grawitacyjnej. Przypadkowo odnajduje tajny ośrodek badawczy, w którym pracuje grupa naukowców pod kierownictwem profesora Branda (Michael Caine). I ch plan zakłada wykorzystanie wytworzonego w okolicach Saturna tunelu czasoprzestrzennego umożliwiającego podróże międzygalaktyczne i znalezienie świata mogącego posłużyć ocalałym ludziom za drugi dom. Cooper otrzymuje propozycję uczestniczenia w ekspedycji jako pilot statku Endurance. Oprócz niego w operacji ma wziąć udział córka profesora Branda, Amelia (Anne Hathaway), oraz dwaj inni naukowcy, Doyle (Wes Bentley) i Romilly (David Gyasi). Mimo perspektywy wieloletniej rozłąki z ukochanymi dziećmi Cooper zgadza się i wyrusza w najważniejszy lot kosmiczny w dziejach ludzkości.',
    'Interstellar'
);
insert into film_category values((select id from films where title = 'Interstellar'),(select id from categories where name = 'Dramat'));
insert into film_category values((select id from films where title = 'Interstellar'),(select id from categories where name = 'Sci-Fi'));
