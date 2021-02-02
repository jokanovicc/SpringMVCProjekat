package com.ftn.Knjizara.dao;

import java.sql.Date;
import java.util.List;

import com.ftn.Knjizara.model.ListaZelja;
import com.ftn.Knjizara.model.SpecijalniDatum;

public interface SpecijalniDatumDAO {
	
    public SpecijalniDatum findOne(Long id);
    public SpecijalniDatum nadji(Date date);
    public List<SpecijalniDatum> findAll();
    public int save(SpecijalniDatum specijalni);

}
