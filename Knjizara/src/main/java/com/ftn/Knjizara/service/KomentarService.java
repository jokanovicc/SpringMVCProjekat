package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.Komentar;
public interface KomentarService {
	
	
    public Komentar findOne(Long id);
    public List<Komentar> findAll();
    public Komentar izvuciZaKorisnika(String korisnicko);
    public Komentar save(Komentar komentar);
    public Komentar update(Komentar komentar);
    public Komentar delete(Long id);

}
