package com.ftn.Knjizara.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.KupljenaKnjigaDAO;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.service.KupljenaKnjigaService;



@Service

public class DatabaseKupljenaKnjigaService implements KupljenaKnjigaService {

	
	@Autowired
	private KupljenaKnjigaDAO kupljenaKnjigaDAO;
	
	
	@Override
	public KupljenaKnjiga findOne(Long id) {
		return kupljenaKnjigaDAO.findOne(id);
	}

	@Override
	public List<KupljenaKnjiga> findAll() {
		return kupljenaKnjigaDAO.findAll();
	}

	@Override
	public KupljenaKnjiga save(KupljenaKnjiga kupljenaKnjiga) {
		kupljenaKnjigaDAO.save(kupljenaKnjiga);
		return kupljenaKnjiga;
	}

	@Override
	public KupljenaKnjiga update(KupljenaKnjiga kupljenaKnjiga) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KupljenaKnjiga delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KupljenaKnjiga> izvuciKupljeneKnjige(Long id) {
		return kupljenaKnjigaDAO.izvuciKupljeneKnjige(id);
	}

	@Override
	public KupljenaKnjiga izvuciIzvestavanja(Date datum1, Date datum2) {
		return kupljenaKnjigaDAO.izvuciIzvestavanja(datum1, datum2);
	}

	@Override
	public List<KupljenaKnjiga> izvuciIzvestavanja2(Date datum1, Date datum2) {
		return kupljenaKnjigaDAO.izvuciIzvestavanja2(datum1, datum2);

	}

}
