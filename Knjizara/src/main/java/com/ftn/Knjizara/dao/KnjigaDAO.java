package com.ftn.Knjizara.dao;

import com.ftn.Knjizara.model.Knjiga;

import java.util.HashMap;
import java.util.List;

public interface KnjigaDAO {
	
	public Knjiga findOne(Long id);
	public List<Knjiga> findAll();
	public List<Knjiga> find(String naziv);
	public int save(Knjiga knjiga);
	public int update(Knjiga knjiga);
	public int delete(long id);

}
