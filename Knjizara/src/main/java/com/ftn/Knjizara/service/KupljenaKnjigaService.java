package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.KupljenaKnjiga;


public interface KupljenaKnjigaService {
	KupljenaKnjiga findOne(Long id);
	List<KupljenaKnjiga> findAll();
	List<KupljenaKnjiga> izvuciKupljeneKnjige(Long id);
	KupljenaKnjiga save(KupljenaKnjiga kupljenaKnjiga);
	KupljenaKnjiga update(KupljenaKnjiga kupljenaKnjiga);
	KupljenaKnjiga delete(Long id);

}
