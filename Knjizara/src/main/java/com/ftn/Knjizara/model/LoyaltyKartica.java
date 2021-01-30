package com.ftn.Knjizara.model;

public class LoyaltyKartica {
	
	private Long id;
	public Korisnik korisnik;
	public double popust;
	public int brojPoena;
	public boolean odobrena;
	public boolean iskoriscena;
	
	public LoyaltyKartica(Long id, Korisnik korisnik, double popust, int brojPoena) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.popust = popust;
		this.brojPoena = brojPoena;
	}
	
	

	public LoyaltyKartica(Long id, Korisnik korisnik, double popust, int brojPoena, boolean odobrena) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.popust = popust;
		this.brojPoena = brojPoena;
		this.odobrena = odobrena;
	}


	
	

	public boolean isIskoriscena() {
		return iskoriscena;
	}



	public void setIskoriscena(boolean iskoriscena) {
		this.iskoriscena = iskoriscena;
	}



	public boolean isOdobrena() {
		return odobrena;
	}

	public void setOdobrena(boolean odobrena) {
		this.odobrena = odobrena;
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
