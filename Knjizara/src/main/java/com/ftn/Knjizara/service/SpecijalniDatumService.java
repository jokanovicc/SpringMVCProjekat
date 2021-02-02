package com.ftn.Knjizara.service;

import java.sql.Date;
import java.util.List;

import com.ftn.Knjizara.model.ListaZelja;
import com.ftn.Knjizara.model.SpecijalniDatum;

public interface SpecijalniDatumService {
	
    public SpecijalniDatum findOne(Long id);
    public SpecijalniDatum nadji(Date date);
    public List<SpecijalniDatum> findAll();
    public SpecijalniDatum save(SpecijalniDatum specijalniDatum);
	
}
