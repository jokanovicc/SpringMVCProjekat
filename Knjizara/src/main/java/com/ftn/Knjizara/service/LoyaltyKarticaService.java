package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.LoyaltyKartica;

public interface LoyaltyKarticaService {
    public LoyaltyKartica findOne(Long id);
    public List<LoyaltyKartica> findAll();
    public LoyaltyKartica izvuciKorisnikovu(String korisnicko);
    public LoyaltyKartica save(LoyaltyKartica loyaltyKartica);
    public LoyaltyKartica update(LoyaltyKartica loyaltyKartica);
    public LoyaltyKartica delete(Long id);

}
