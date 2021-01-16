package com.ftn.Knjizara.dao;

import java.util.List;

import com.ftn.Knjizara.model.Knjiga;



public interface KnjigaDAO2 {
	public Knjiga findOne(Long id);

	public List<Knjiga> findAll();
	
	public List<Knjiga> findAll2();

	public int save(Knjiga knjiga);

	public int update(Knjiga knjiga);
	
	public int delete(Long id);
	
	public List<Knjiga> find(String naziv, Long zanrId, String autor,String jezik, Integer cenaOd, Integer cenaDo,String isbn);
	


}
