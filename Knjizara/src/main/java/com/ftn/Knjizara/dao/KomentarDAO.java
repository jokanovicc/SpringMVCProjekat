package com.ftn.Knjizara.dao;

import java.util.List;

import com.ftn.Knjizara.model.Komentar;

public interface KomentarDAO {
	
	
    public Komentar findOne(Long id);
    public List<Komentar> findAll();
    public Komentar izvuciOdKorisnika(String korisnicko);
    public int save(Komentar komentar);
    public int update(Komentar komentar);
    public int delete(Long id);
	
}
