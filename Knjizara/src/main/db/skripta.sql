DROP SCHEMA IF EXISTS knjizara;
CREATE SCHEMA knjizara DEFAULT CHARACTER SET utf8;
USE knjizara;




create table korisnici(
		korisnickoIme VARCHAR(20),
		lozinka VARCHAR(20) NOT NULL,
		eMail VARCHAR(20) NOT NULL,
        ime VARCHAR(20) NOT NULL,
        prezime VARCHAR(20) NOT NULL,
        datumRodjenja date not null,
        adresa VARCHAR(20) NOT NULL,
        brojTelefona VARCHAR(20) NOT NULL,
		vremeRegistracije dateTime not null,
		administrator BOOL DEFAULT false,
		PRIMARY KEY(korisnickoIme)

);

create table knjige(
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(75) NOT NULL,
	isbn varchar(13),
    izdavac varchar(75) not null,
    autor varchar(75) not null,
    godinaIzdavanja int not null,
	kratakOpis text,
    cena double not null,
    brojStrana int not null,
	jezik varchar(50) not null,
	ocenaProsecna double,
	pismo varchar(20),
	tipPoveza varchar(20),
	slika varchar(100),
	kolicina int not null,
	PRIMARY KEY(id)

);

create table specijalnidatum(
	id bigint auto_increment,
    datum date,
    popust bigint,
    primary key(id)

)










create table zanrovi(
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(25) NOT NULL,
    opis VARCHAR(60) NOT NULL,
	PRIMARY KEY(id)
);

create table knjigaZanr(

	knjigaId BIGINT,
    zanrId BIGINT,
    PRIMARY KEY(knjigaId, zanrId),
    FOREIGN KEY(knjigaId) REFERENCES knjige(id)
		ON DELETE CASCADE,
    FOREIGN KEY(zanrId) REFERENCES zanrovi(id)
		ON DELETE CASCADE
);


create table LoyaltyKartica(
	id BIGINT AUTO_INCREMENT,
    popust double not null,
    brojPoena int not null,
	korisnik varchar(20) not null,
        FOREIGN KEY(korisnik) REFERENCES korisnici(korisnickoIme)
		ON DELETE CASCADE,
	PRIMARY KEY(id)

);

create table KupljenaKnjiga(

	id BIGINT AUTO_INCREMENT,
	knjigaID bigint not null,
	kupovinaId bigint not null,
    brojPrimeraka int not null,
    cena double not null,
        FOREIGN KEY(knjigaID) REFERENCES knjige(id)
		ON DELETE CASCADE,
        FOREIGN KEY(kupovinaId) REFERENCES kupovina(id)
		ON DELETE CASCADE,
	primary key(id)

);

create table kupovina(
	id bigint,
    ukupnaCena double not null,
    datumKupovine datetime,
    korisnik varchar(20),
    ukupanBrojKnjiga int,
	FOREIGN KEY(korisnik) REFERENCES korisnici(korisnickoIme)
	ON DELETE CASCADE,
    PRIMARY KEY(id)


)



create table listazelja(
	id BIGINT AUTO_INCREMENT,
	korisnik varchar(20) not null,
    knjigaID bigint not null,
	FOREIGN KEY(knjigaID) REFERENCES knjige(id)
	ON DELETE CASCADE,
	FOREIGN KEY(korisnik) REFERENCES korisnici(korisnickoIme)
	ON DELETE CASCADE,
	PRIMARY KEY(id)
    

)















create table Komentar(
	id BIGINT AUTO_INCREMENT,
    ocena float not null,
    datum datetime not null,
    korisnik varchar(50) not null,
    knjigaId bigint not null,
    statusKomentara ENUM('odobren', 'naCekanju','nijeOdobren') DEFAULT 'naCekanju',
    primary key(id),
	FOREIGN KEY(korisnik) REFERENCES korisnici(korisnickoIme)
	ON DELETE CASCADE,
	FOREIGN KEY(knjigaId) REFERENCES knjige(id)
	ON DELETE CASCADE
);













insert into korisnici(korisnickoIme,lozinka,eMail,ime,prezime,datumRodjenja,adresa,brojTelefona,vremeRegistracije,administrator) values('pera', 'pera', 'pera@gmail.com', 'Petar', 'Petrovic', '1978-05-05', 'deligradska', '0651111', '2020-12-31 12:35:00', true);
insert into korisnici(korisnickoIme,lozinka,eMail,ime,prezime,datumRodjenja,adresa,brojTelefona,vremeRegistracije,administrator) values('jovo', 'jovo123', 'jovan@gmail.com', 'Jovan', 'Jovanovic', '2000-07-22', 'Gogoljeva', '0650004', '2021-01-15 17:24:57', false);


insert into zanrovi(id,naziv,opis) values (1, 'roman', 'roman knjiga');
insert into zanrovi(id,naziv,opis) values (2, 'krimi', 'krimi knjiga');
insert into zanrovi(id,naziv,opis) values (3, 'istorijski', 'istorijska knjiga');
insert into zanrovi(id,naziv,opis) values (4, 'triler', 'triler knjiga');

insert into knjige(id,naziv,isbn,izdavac,autor,godinaIzdavanja,kratakOpis,cena,brojStrana,jezik,ocenaProsecna,pismo,tipPoveza,slika,kolicina) values (1, 'Na Drini ćuprija', '4534535', 'Laguna', 'Ivo Andrić', 2019, 'Visegrad u tursko doba', 950, 430, 'srpski', 1, 'latinica', 'meki', 'https://imgur.com/kTquRc0.png', 17);
insert into knjige(id,naziv,isbn,izdavac,autor,godinaIzdavanja,kratakOpis,cena,brojStrana,jezik,ocenaProsecna,pismo,tipPoveza,slika,kolicina) values (2, 'Stranac', '111', 'skolska', 'Albert Kami', 2010, 'U strogo književnoumetničkom smislu roman Stranac najbolje je... ', 1750, 250, 'srpski', 4.35, 'cirilica', 'meki', 'https://img.wattpad.com/cover/181703545-352-k792002.jpg', 10);
insert into knjige(id,naziv,isbn,izdavac,autor,godinaIzdavanja,kratakOpis,cena,brojStrana,jezik,ocenaProsecna,pismo,tipPoveza,slika,kolicina) values (3, 'Moby Dick', '4950498228552', 'Alma Books', 'Herman Melville', 2017, 'When the young Ishmael gets on board...', 470, 704, 'engleski', 1, 'latinica', 'tvrdi', 'https://imgur.com/LfrIt2t.png', 40);
insert into knjige(id,naziv,isbn,izdavac,autor,godinaIzdavanja,kratakOpis,cena,brojStrana,jezik,ocenaProsecna,pismo,tipPoveza,slika,kolicina) values (4, 'Kad su cvetale Tikve', '3231123', 'laguna', 'Dragoslav Mihailovic', 2010, 'Radnja romana je smeštena u posleratnu Jugoslaviju. Ljuba...', 2000, 200, 'srpski', 4.6, 'cirilica', 'meki', 'https://www.delfi.rs/_img/artikli/2015/06/kad_su_cvetale_tikve_vv.jpg', 0);
insert into knjige(id,naziv,isbn,izdavac,autor,godinaIzdavanja,kratakOpis,cena,brojStrana,jezik,ocenaProsecna,pismo,tipPoveza,slika,kolicina) values (5, 'A Young Doctor Notebook', '4534535', 'Alma Books', 'Mikhail Bulgakov', 2012, 'Using a sharply realistic and humorous style...', 580, 160, 'ruski', 1, 'cirilica', 'meki', 'https://imgur.com/PZBvTC4.png', 25);


insert into knjigazanr(knjigaId, zanrId) values (1, 2);
insert into knjigazanr(knjigaId, zanrId) values (2, 2);
insert into knjigazanr(knjigaId, zanrId) values (2, 3);
insert into knjigazanr(knjigaId, zanrId) values (3, 1);
insert into knjigazanr(knjigaId, zanrId) values (4, 4);
insert into knjigazanr(knjigaId, zanrId) values (5, 1);
insert into knjigazanr(knjigaId, zanrId) values (5, 2);