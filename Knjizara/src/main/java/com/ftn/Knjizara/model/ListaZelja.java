package com.ftn.Knjizara.model;

public class ListaZelja {
	
	private Long id;
	private Knjiga knjiga;
	private Korisnik korisnik;
	
	
	
	public ListaZelja(Long id, Knjiga knjiga, Korisnik korisnik) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.korisnik = korisnik;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Knjiga getKnjiga() {
		return knjiga;
	}



	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}



	public Korisnik getKorisnik() {
		return korisnik;
	}



	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}



	@Override
	public String toString() {
		return "ListaZelja [id=" + id + ", knjiga=" + knjiga + ", korisnik=" + korisnik + "]";
	}
	
	
	
	
	
	

}
