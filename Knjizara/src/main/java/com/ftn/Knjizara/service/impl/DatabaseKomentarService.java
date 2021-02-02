package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.KomentarDAO;
import com.ftn.Knjizara.model.Komentar;
import com.ftn.Knjizara.service.KomentarService;

@Service
public class DatabaseKomentarService implements KomentarService {
	
	@Autowired
	private KomentarDAO komentarDAO;
	
	

	@Override
	public Komentar findOne(Long id) {
		return komentarDAO.findOne(id);
	}

	@Override
	public List<Komentar> findAll() {
		return komentarDAO.findAll();
	}

	@Override
	public Komentar izvuciZaKorisnika(String korisnicko) {
		// TODO Auto-generated method stub
		return komentarDAO.izvuciOdKorisnika(korisnicko);
	}

	@Override
	public Komentar save(Komentar komentar) {
		komentarDAO.save(komentar);
		return komentar;
	}

	@Override
	public Komentar update(Komentar komentar) {
		komentarDAO.update(komentar);
		return komentar;
	}

	@Override
	public Komentar delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
