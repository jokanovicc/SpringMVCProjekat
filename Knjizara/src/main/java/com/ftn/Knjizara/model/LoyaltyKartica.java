package com.ftn.Knjizara.model;

public class LoyaltyKartica {
	
	private Long id;
	public Korisnik korisnik;
	public double popust;
	public int brojPoena;
	
	
	public LoyaltyKartica(Long id, Korisnik korisnik, double popust, int brojPoena) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.popust = popust;
		this.brojPoena = brojPoena;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Korisnik getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}


	public double getPopust() {
		return popust;
	}


	public void setPopust(double popust) {
		this.popust = popust;
	}


	public int getBrojPoena() {
		return brojPoena;
	}


	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}


	@Override
	public String toString() {
		return "LoyaltyKartica [id=" + id + ", korisnik=" + korisnik + ", popust=" + popust + ", brojPoena=" + brojPoena
				+ "]";
	}
	
	
	
	
	
	
	

}
