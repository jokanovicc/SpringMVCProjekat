package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.KnjigaDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.KnjigaService;

@Service
public class DatabaseKnjigaService implements KnjigaService {
	
	@Autowired
	private KnjigaDAO knjigaDAO;

	@Override
	public Knjiga findOne(Long id) {
		return knjigaDAO.findOne(id);	}

	@Override
	public List<Knjiga> findAll() {
		return knjigaDAO.findAll();
	}

	@Override
	public Knjiga save(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Knjiga> save(List<Knjiga> knjiga) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Knjiga update(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Knjiga> update(List<Knjiga> knjiga) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Knjiga delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Knjiga> deleteAll(Zanr zanr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Knjiga> findByZanrId(Long zanrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Knjiga> find(String naziv) {
		if (naziv == null) {
			return knjigaDAO.findAll();
		}
		return knjigaDAO.find(naziv);
	}
	
	

}
