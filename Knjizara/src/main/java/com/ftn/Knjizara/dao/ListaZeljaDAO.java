package com.ftn.Knjizara.dao;

import java.util.List;

import com.ftn.Knjizara.model.ListaZelja;

public interface ListaZeljaDAO {
    public ListaZelja findOne(Long id);
    public List<ListaZelja> findAll();
    public List<ListaZelja> izvuciKorisnikove(String korisnicko);
    public int save(ListaZelja listaZelja);
    public int update(ListaZelja listaZelja);
    public int delete(Long id);

}
