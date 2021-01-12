package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;



public interface Knjiga2Service {
	Knjiga findOne(Long id);
	List<Knjiga> findAll();
	Knjiga save(Knjiga knjiga);
	List<Knjiga> save(List<Knjiga> knjige);
	Knjiga update(Knjiga knjiga);
	List<Knjiga> update(List<Knjiga> knjige);
	Knjiga delete(Long id);
	List<Knjiga> deleteAll(Zanr zanr);
	void delete(List<Long> ids);
	List<Knjiga> find(String naziv, Long zanrId, String autor,String jezik, Integer cenaOd, Integer cenaDo);
	List<Knjiga> sort(String sortKriterijum, String ascDesc,List<Knjiga> nesortiran);

	List<Knjiga> findByZanrId(Long zanrId);

}
