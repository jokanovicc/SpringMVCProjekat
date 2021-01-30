package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.LoyaltyKarticaDAO;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.LoyaltyKartica;
import com.ftn.Knjizara.service.LoyaltyKarticaService;

@Service
public class DatabaseLoyaltyKarticaService implements LoyaltyKarticaService {

	@Autowired
	private LoyaltyKarticaDAO loyaltyKarticaDAO;
	
	
	@Override
	public LoyaltyKartica findOne(Long id) {
		return loyaltyKarticaDAO.findOne(id);

	}

	@Override
	public List<LoyaltyKartica> findAll() {
		return loyaltyKarticaDAO.findAll();

	}

	@Override
	public LoyaltyKartica izvuciKorisnikovu(String korisnicko) {
		return loyaltyKarticaDAO.izvuciKorisnikovu(korisnicko);
	}

	@Override
	public LoyaltyKartica save(LoyaltyKartica loyaltyKartica) {
		loyaltyKarticaDAO.save(loyaltyKartica);
		return loyaltyKartica;
	}

	@Override
	public LoyaltyKartica update(LoyaltyKartica loyaltyKartica) {
		loyaltyKarticaDAO.update(loyaltyKartica);
		return loyaltyKartica;
	}

	@Override
	public LoyaltyKartica delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
}
