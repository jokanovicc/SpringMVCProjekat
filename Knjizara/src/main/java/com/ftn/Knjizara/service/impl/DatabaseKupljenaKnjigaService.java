package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftn.Knjizara.dao.KupljenaKnjigaDAO;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.service.KupljenaKnjigaService;

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

}
