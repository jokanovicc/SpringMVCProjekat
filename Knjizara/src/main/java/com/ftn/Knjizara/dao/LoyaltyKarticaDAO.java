package com.ftn.Knjizara.dao;

import java.util.List;

import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.LoyaltyKartica;


public interface LoyaltyKarticaDAO {
	
    public LoyaltyKartica findOne(Long id);
    public List<LoyaltyKartica> findAll();
    public LoyaltyKartica izvuciKorisnikovu(String korisnicko);
    public int save(LoyaltyKartica loyaltyKartica);
    public int update(LoyaltyKartica loyaltyKartica);
    public int delete(Long id);
}
