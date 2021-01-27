package com.ftn.Knjizara.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.ftn.Knjizara.model.Korisnik;


public interface KorisnikService {
	
	
	Korisnik findOne(String korisnickoIme);
	Korisnik findOne(String korisnickoIme, String lozinka);
	List<Korisnik> findAll();
	Korisnik save(Korisnik korisnik);
	List<Korisnik> save(List<Korisnik> korisnici);
	Korisnik update(Korisnik korisnik);
	List<Korisnik> update(List<Korisnik> korisnici);
	Korisnik delete(String korisnickoIme);
	void delete(List<String> korisnickaImena);
	List<Korisnik> find(String korisnickoIme, String eMail,String ime,String prezime,String adresa, String brojTelefona,Date datumRodjenja,Boolean administrator,LocalDateTime datumRegistracije);
	List<Korisnik> findByKorisnickoIme(String korisnickoIme);

}
