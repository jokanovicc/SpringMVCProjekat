package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftn.Knjizara.dao.KupovinaDAO;
import com.ftn.Knjizara.model.Kupovina;
import com.ftn.Knjizara.service.KupovinaService;

public class DatabaseKupovinaService implements KupovinaService {
	
	@Autowired
	private KupovinaDAO kupovinaDAO;
	

	@Override
	public Kupovina findOne(Long id) {
		return kupovinaDAO.findOne(id);
	}

	@Override
	public List<Kupovina> findAll() {
		return kupovinaDAO.findAll();
	}

	@Override
	public Kupovina save(Kupovina kupovina) {
		kupovinaDAO.save(kupovina);
		return kupovina;
	}

	@Override
	public Kupovina update(Kupovina kupovina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kupovina delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
