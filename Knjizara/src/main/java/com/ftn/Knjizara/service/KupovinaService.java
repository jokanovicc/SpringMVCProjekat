package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.Kupovina;



public interface KupovinaService {
	Kupovina findOne(Long id);
	List<Kupovina> findAll();
	List<Kupovina> findAllzaKorisnika(String korisnicko);
	Kupovina save(Kupovina kupovina);
	Kupovina update(Kupovina kupovina);
	Kupovina delete(Long id);

}
