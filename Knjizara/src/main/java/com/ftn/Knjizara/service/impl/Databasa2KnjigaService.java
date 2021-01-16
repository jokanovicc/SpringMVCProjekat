package com.ftn.Knjizara.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.KnjigaDAO2;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.Knjiga2Service;



@Service
public class Databasa2KnjigaService implements Knjiga2Service {

	@Autowired
	private KnjigaDAO2 knjigaDAO;
	
	
	@Override
	public Knjiga findOne(Long id) {
		return knjigaDAO.findOne(id);
	}

	@Override
	public List<Knjiga> findAll() {
		return knjigaDAO.findAll();
	}

	@Override
	public Knjiga save(Knjiga knjiga) {
		knjigaDAO.save(knjiga);
		return knjiga;
	}

	@Override
	public List<Knjiga> save(List<Knjiga> knjige) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Knjiga update(Knjiga knjiga) {
		knjigaDAO.update(knjiga);
		return knjiga;
	}

	@Override
	public List<Knjiga> update(List<Knjiga> knjige) {
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
	public List<Knjiga> find(String naziv, Long zanrId, String autor,String jezik, Integer cenaOd, Integer cenaDo,String isbn) {
		//NACIN 1
		List<Knjiga> knjige = knjigaDAO.findAll();
		List<Knjiga> knjige2 = knjigaDAO.findAll2();

		// maksimalno inkluzivne vrednosti parametara ako su izostavljeni
		// filtiranje radi u Servisnom sloju - izbegavati
		if (naziv == null) {
			naziv = "";
		}
		if (zanrId == null) {
			zanrId = 0L;
		}
		
		if (autor == null) {
			autor = "";
		}
		if (jezik == null) {
			jezik = "";
		}
		
		if (isbn == null) {
			isbn = "";
		}
		
		
		if (cenaOd == null) {
			cenaOd = 0;
		}
		if (cenaDo == null) {
			cenaDo = Integer.MAX_VALUE;
		}
		
		List<Knjiga> rezultat = new ArrayList<>();
		for (Knjiga itKnjiga: knjige) {
			// kriterijum pretrage
			if (!itKnjiga.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
				continue;
			}
			
			
			
			if (!itKnjiga.getAutor().toLowerCase().contains(autor.toLowerCase())) {
				continue;
			}
			
			if (!itKnjiga.getJezik().toLowerCase().contains(jezik.toLowerCase())) {
				continue;
			}
			
			if (zanrId > 0) { // ako je žanr odabran
				boolean pronadjen = false;
				for (Zanr itZanr: itKnjiga.getZanrovi()) {
					if (itZanr.getId() == zanrId) {
						pronadjen = true;
						break;
					}
				}
				if (!pronadjen) {
					continue;
				}
			}
			if (!(itKnjiga.getCena() >= cenaOd && itKnjiga.getCena() <= cenaDo)) {
				continue;
			}

			rezultat.add(itKnjiga);
		}
		
	//	List<Knjiga> rezultat2 = new ArrayList<>();

		if(isbn != "") {
				for (Knjiga itKnjiga2: knjige2) {
					// kriterijum pretrage
		
					
					if (!itKnjiga2.getISBN().toLowerCase().contains(isbn.toLowerCase())) {
						continue;
					}
					
					rezultat.clear();
					rezultat.add(itKnjiga2);
				}
		
		}

		return rezultat;
	}

	@Override
	public List<Knjiga> findByZanrId(Long zanrId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Knjiga> findAll2() {
		return knjigaDAO.findAll2();
	}

}
