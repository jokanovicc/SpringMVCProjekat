package com.ftn.Knjizara.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Kupovina {
	
	private Long id;
	private Double ukupnaCena;
	private LocalDateTime datumKupovine;
	private Korisnik korisnik;
	private int brojKnjiga;
	
	
	private List<KupljenaKnjiga> kupljeneKnjige = new ArrayList<>();


	
	
	




	public Kupovina(Long id, Double ukupnaCena, LocalDateTime datumKupovine, Korisnik korisnik, int brojKnjiga) {
		super();
		this.id = id;
		this.ukupnaCena = ukupnaCena;
		this.datumKupovine = datumKupovine;
		this.korisnik = korisnik;
		this.brojKnjiga = brojKnjiga;
	}






	public Kupovina(Long id, Double ukupnaCena, LocalDateTime datumKupovine, Korisnik korisnik, int brojKnjiga,
			List<KupljenaKnjiga> kupljeneKnjige) {
		super();
		this.id = id;
		this.ukupnaCena = ukupnaCena;
		this.datumKupovine = datumKupovine;
		this.korisnik = korisnik;
		this.brojKnjiga = brojKnjiga;
		this.kupljeneKnjige = kupljeneKnjige;
	}






	public Double getUkupnaCena() {
		return ukupnaCena;
	}






	public void setUkupnaCena(Double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}






	public Kupovina(Long id, LocalDateTime datumKupovine, Korisnik korisnik, int brojKnjiga,
			List<KupljenaKnjiga> kupljeneKnjige) {
		super();
		this.id = id;

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
