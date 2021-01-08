package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.service.KorisnikService;


@Service
public class DatabaseKorisnikService implements KorisnikService {

	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	
	
	@Override
	public Korisnik findOne(String korisnickoIme) {
		return korisnikDAO.findOne(korisnickoIme);
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		return korisnikDAO.findOne(korisnickoIme, lozinka);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikDAO.findAll();
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		korisnikDAO.save(korisnik);
		return korisnik;
	}

	@Override
	public List<Korisnik> save(List<Korisnik> korisnici) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik update(Korisnik korisnik) {
		korisnikDAO.update(korisnik);
		return korisnik;
	}

	@Override
	public List<Korisnik> update(List<Korisnik> korisnici) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik delete(String korisnickoIme) {
		Korisnik korisnik = findOne(korisnickoIme);
		if (korisnik != null) {
			korisnikDAO.delete(korisnickoIme);
		}
		return korisnik;
	}

	@Override
	public void delete(List<String> korisnickaImena) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Korisnik> find(String korisnickoIme, String eMail, String pol, Boolean administrator) {
		// TODO Auto-generated method stub
		return korisnikDAO.find(korisnickoIme, eMail, pol, administrator);

	}

	@Override
	public List<Korisnik> findByKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

}
