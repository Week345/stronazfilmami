create database if not exists filmDB;

create user if not exists 'filmuser'@'%' identified by 'Password123';

grant all privileges on filmDB.* to 'filmuser'@'%';

use filmDB;

create table if not exists films (prodYear integer,
    rating decimal(5,2),
    ratingSum integer,
    ratingsNumber integer,
    IMDBRating integer,
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

insert into films(IMDBRating, rating, ratingSum, ratingsNumber, prodYear,awards,description,title) values (
    19,
    4.0,
    26172,
    6543,
    2014,
    'Saturn x3',
    'Niedaleka przyszłość. Wskutek przemian klimatycznych Ziemia jest systematycznie niszczona przez suszę i głód. Głównym priorytetem stojącej przed obliczem zagłady ludzkości staje się wyprodukowanie wystarczającej ilości pożywienia. Tymczasem były pilot NASA zajmujący się obecnie farmą, Cooper (Matthew McConaughey), wpada na trop dziwnej anomalii grawitacyjnej. Przypadkowo odnajduje tajny ośrodek badawczy, w którym pracuje grupa naukowców pod kierownictwem profesora Branda (Michael Caine). I ch plan zakłada wykorzystanie wytworzonego w okolicach Saturna tunelu czasoprzestrzennego umożliwiającego podróże międzygalaktyczne i znalezienie świata mogącego posłużyć ocalałym ludziom za drugi dom. Cooper otrzymuje propozycję uczestniczenia w ekspedycji jako pilot statku Endurance. Oprócz niego w operacji ma wziąć udział córka profesora Branda, Amelia (Anne Hathaway), oraz dwaj inni naukowcy, Doyle (Wes Bentley) i Romilly (David Gyasi). Mimo perspektywy wieloletniej rozłąki z ukochanymi dziećmi Cooper zgadza się i wyrusza w najważniejszy lot kosmiczny w dziejach ludzkości.',
    'Interstellar'
);
insert into film_category values((select id from films where title = 'Interstellar'),(select id from categories where name = 'Dramat'));
insert into film_category values((select id from films where title = 'Interstellar'),(select id from categories where name = 'Sci-Fi'));

insert into films(IMDBRating, rating, ratingSum, ratingsNumber, prodYear,awards,description,title) values (
    3,
    4.9,
    47216,
    9636,
    2008,
    'Saturn x5',
    'Bruce Wayne (Christian Bale) po raz kolejny w roli Batmana toczy zażartą wojnę ze światem przestępczym w Gotham City. Gdy we współpracy z porucznikiem Jimem Gordonem i prokuratorem okręgowym Harveyem Dentem udaje się rozbić kryminalne podziemie miasta, na horyzoncie pojawia się nowy geniusz zbrodni, Joker. Celem nowego wroga Batmana jest zmuszenia go, by ten przeszedł na złą stronę mocy i przestał być bohaterem a stał się złoczyńcą. Czy plan Jokera odniesie sukces?',
    'Mroczny Rycerz'
);

insert into film_category values((select id from films where title = 'Mroczny Rycerz'),(select id from categories where name = 'Sci-Fi'));
insert into film_category values((select id from films where title = 'Mroczny Rycerz'),(select id from categories where name = 'Akcja'));


insert into films(IMDBRating, rating, ratingSum, ratingsNumber, prodYear,awards,description,title) values (
    14,
    4.8,
    38740,
    8071,
    2010,
    'Oscar x4',
    'Dom Cobb to niezwykle utalentowany złodziej, który do doskonałości opanował kradzież najcenniejszych sekretów z głębokich czeluści podświadomości podczas snu. Nietypowa umiejętność uczyniła Cobba pożądanym graczem na zdradzieckim rynku korporacyjnego wywiadu. Jego talent stał się również jego prześladowcą, uczynił z niego uciekiniera i kosztował wszystko, co kiedykolwiek kochał. Jedyną szansą, dzięki której będzie mógł odzyskać wolność i wrócić do życia sprzed lat, będzie ostatnie w karierze zadanie – dokona niemożliwego: incepcji. Dom wspólnie z grupą innych specjalistów, zamiast typowej perfekcyjnej kradzieży pomysłu, będą musieli taki pomysł we śnie zaszczepić.',
    'Incepcja'
);

insert into film_category values((select id from films where title = 'Incepcja'),(select id from categories where name = 'Akcja'));
insert into film_category values((select id from films where title = 'Incepcja'),(select id from categories where name = 'Sci-Fi'));