package com.ftn.Knjizara.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Kupovina {
	
	private Long id;
	private int ocena;
	private LocalDateTime datumKupovine;
	private Korisnik korisnik;
	private int brojKnjiga;
	
	
	private List<KupljenaKnjiga> kupljeneKnjige = new ArrayList<>();


	
	
	
	
	public Kupovina(Long id, int ocena, LocalDateTime datumKupovine, Korisnik korisnik, int brojKnjiga) {
		super();
		this.id = id;
		this.ocena = ocena;
		this.datumKupovine = datumKupovine;
		this.korisnik = korisnik;
		this.brojKnjiga = brojKnjiga;
	}






	public Kupovina(Long id, int ocena, LocalDateTime datumKupovine, Korisnik korisnik, int brojKnjiga,
			List<KupljenaKnjiga> kupljeneKnjige) {
		super();
		this.id = id;
		this.ocena = ocena;
		this.datumKupovine = datumKupovine;
		this.korisnik = korisnik;
		this.brojKnjiga = brojKnjiga;
		this.kupljeneKnjige = kupljeneKnjige;
	}






	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public int getOcena() {
		return ocena;
	}






	public void setOcena(int ocena) {
		this.ocena = ocena;
	}






	public LocalDateTime getDatumKupovine() {
		return datumKupovine;
	}






	public void setDatumKupovine(LocalDateTime datumKupovine) {
		this.datumKupovine = datumKupovine;
	}






	public Korisnik getKorisnik() {
		return korisnik;
	}






	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}






	public int getBrojKnjiga() {
		return brojKnjiga;
	}






	public void setBrojKnjiga(int brojKnjiga) {
		this.brojKnjiga = brojKnjiga;
	}






	public List<KupljenaKnjiga> getKupljeneKnjige() {
		return kupljeneKnjige;
	}



	public void setKupljeneKnjige(List<KupljenaKnjiga> kupljeneKnjige) {
		this.kupljeneKnjige.clear();
		this.kupljeneKnjige.addAll(kupljeneKnjige);
	}
	
	
	
	
	
	

}
