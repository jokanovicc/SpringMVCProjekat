package com.ftn.Knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.Knjizara.dao.ListaZeljaDAO;
import com.ftn.Knjizara.model.ListaZelja;
import com.ftn.Knjizara.service.ListaZeljaService;

@Service
public class DatabaseListaZeljaService implements ListaZeljaService {

	@Autowired
	private ListaZeljaDAO listaZeljaDAO;
	
	
	
	@Override
	public ListaZelja findOne(Long id) {
		return listaZeljaDAO.findOne(id);

	}

	@Override
	public List<ListaZelja> findAll() {
		return listaZeljaDAO.findAll();

	}

	@Override
	public List<ListaZelja> izvuciKorisnikove(String korisnicko) {
		return listaZeljaDAO.izvuciKorisnikove(korisnicko);
	}

	@Override
	public ListaZelja save(ListaZelja listaZelja) {
		listaZeljaDAO.save(listaZelja);
		return listaZelja;
	}

	@Override
	public ListaZelja update(ListaZelja listaZelja) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaZelja delete(Long id) {
		ListaZelja listaZelja = findOne(id);
		if (listaZelja != null) {
			listaZeljaDAO.delete(id);
		}
		return listaZelja;
	}

}
