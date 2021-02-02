package com.ftn.Knjizara.service;

import java.util.List;

import com.ftn.Knjizara.model.ListaZelja;

public interface ListaZeljaService {
	
	
    public ListaZelja findOne(Long id);
    public List<ListaZelja> findAll();
    public List<ListaZelja> izvuciKorisnikove(String korisnicko);
    public ListaZelja save(ListaZelja listaZelja);
    public ListaZelja update(ListaZelja listaZelja);
    public ListaZelja delete(Long id);

}
