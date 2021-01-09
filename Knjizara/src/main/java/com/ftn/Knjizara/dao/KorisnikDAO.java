package com.ftn.Knjizara.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.ftn.Knjizara.model.Korisnik;


public interface KorisnikDAO {
	
	public Korisnik findOne(String korisnickoIme);

	public Korisnik findOne(String korisnickoIme, String lozinka);

	public List<Korisnik> findAll();

	public List<Korisnik> find(String korisnickoIme, String eMail,String ime,String prezime, String pol, String adresa, String brojTelefona,Date datumRodjenja,Boolean administrator,LocalDateTime datumRegistracije);
	
	public void save(Korisnik korisnik);

	public void update(Korisnik korisnik);

	public void delete(String korisnickoIme);
	

}
