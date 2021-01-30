package com.ftn.Knjizara.service;

import java.sql.Date;
import java.util.List;

import com.ftn.Knjizara.model.KupljenaKnjiga;


public interface KupljenaKnjigaService {
	KupljenaKnjiga findOne(Long id);
	List<KupljenaKnjiga> findAll();
	List<KupljenaKnjiga> izvuciKupljeneKnjige(Long id);
	KupljenaKnjiga izvuciIzvestavanja(Date datum1, Date datum2);
	List<KupljenaKnjiga> izvuciIzvestavanja2(Date datum1, Date datum2);
	KupljenaKnjiga save(KupljenaKnjiga kupljenaKnjiga);
	KupljenaKnjiga update(KupljenaKnjiga kupljenaKnjiga);
	KupljenaKnjiga delete(Long id);

}
