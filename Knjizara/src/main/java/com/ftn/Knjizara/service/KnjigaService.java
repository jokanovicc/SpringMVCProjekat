package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;



public interface KnjigaService {
	
	
	Knjiga findOne(Long id);
	List<Knjiga> findAll();
	Knjiga save(Knjiga knjiga);
	List<Knjiga> save(List<Knjiga> knjiga);
	Knjiga update(Knjiga knjiga);
	List<Knjiga> update(List<Knjiga> knjiga);
	Knjiga delete(Long id);
	List<Knjiga> deleteAll(Zanr zanr);
	void delete(List<Long> ids);
	List<Knjiga> find(String naziv);
	List<Knjiga> findByZanrId(Long zanrId);


}
