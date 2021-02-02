package com.ftn.Knjizara.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.SpecijalniDatumDAO;
import com.ftn.Knjizara.model.ListaZelja;
import com.ftn.Knjizara.model.SpecijalniDatum;
import com.ftn.Knjizara.service.SpecijalniDatumService;

@Service
public class DatabaseSpecijalniDatumService implements SpecijalniDatumService {

	@Autowired
	private SpecijalniDatumDAO specijalniDatumDAO;
	
	
	@Override
	public SpecijalniDatum findOne(Long id) {
		return specijalniDatumDAO.findOne(id);
	}

	@Override
	public SpecijalniDatum nadji(Date date) {
		return specijalniDatumDAO.nadji(date);

	}

	@Override
	public List<SpecijalniDatum> findAll() {
		return specijalniDatumDAO.findAll();
	}

	@Override
	public SpecijalniDatum save(SpecijalniDatum specijalniDatum) {
		specijalniDatumDAO.save(specijalniDatum);
		return specijalniDatum;
	}

}
