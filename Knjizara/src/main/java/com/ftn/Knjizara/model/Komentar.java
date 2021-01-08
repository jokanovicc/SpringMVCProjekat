package com.ftn.Knjizara.model;

import java.time.LocalDateTime;

public class Komentar {
	
	private Long id;
	private String tekstKomentara;
	private double ocena;
	private LocalDateTime vremeKomentara;
	private Korisnik korisnik;
	private Knjiga knjiga;
	private StatusKomentara status;
	
	



	
	
	public Komentar(Long id, String tekstKomentara, double ocena, LocalDateTime vremeKomentara, Korisnik korisnik,
			Knjiga knjiga, StatusKomentara status) {
		super();
		this.id = id;
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
		this.vremeKomentara = vremeKomentara;
		this.korisnik = korisnik;
		this.knjiga = knjiga;
		this.status = status;
	}

	
	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getTekstKomentara() {
		return tekstKomentara;
	}


	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}


	public double getOcena() {
		return ocena;
	}


	public void setOcena(double ocena) {
		this.ocena = ocena;
	}


	public LocalDateTime getVremeKomentara() {
		return vremeKomentara;
	}


	public void setVremeKomentara(LocalDateTime vremeKomentara) {
		this.vremeKomentara = vremeKomentara;
	}


	public Korisnik getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}


	public Knjiga getKnjiga() {
		return knjiga;
	}


	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}


	public StatusKomentara getStatus() {
		return status;
	}


	public void setStatus(StatusKomentara status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	

}
