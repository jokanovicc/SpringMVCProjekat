package com.ftn.Knjizara.model;

import java.sql.Date;
import java.time.LocalDateTime;


public class Korisnik {
	
	private String korisnickoIme="", lozinka="", eMail="", ime = "",prezime = "",adresa = "",brojTelefona = "";
	private Date datumRodjenja;
	private LocalDateTime datumRegistracije;
	
	private boolean administrator = false;
	private boolean blokiran = false;
	private boolean kartica = false;
	
	

	public Korisnik(String korisnickoIme, String lozinka, String eMail, String ime, String prezime, String adresa,
			String brojTelefona, Date datumRodjenja, LocalDateTime datumRegistracije, boolean administrator, boolean blokiran) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.eMail = eMail;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.datumRodjenja = datumRodjenja;
		this.datumRegistracije = datumRegistracije;
		this.administrator = administrator;
		this.blokiran = blokiran;
	}
	
	
	
	
	
	

	public Korisnik(String korisnickoIme, String lozinka, String eMail, String ime, String prezime, String adresa,
			String brojTelefona, Date datumRodjenja, LocalDateTime datumRegistracije, boolean administrator,
			boolean blokiran, boolean kartica) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.eMail = eMail;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.datumRodjenja = datumRodjenja;
		this.datumRegistracije = datumRegistracije;
		this.administrator = administrator;
		this.blokiran = blokiran;
		this.kartica = kartica;
	}







	public boolean isKartica() {
		return kartica;
	}





	public void setKartica(boolean kartica) {
		this.kartica = kartica;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		return true;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}



	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	
	


	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

	public String getLozinka() {
		return lozinka;
	}



	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}



	public String geteMail() {
		return eMail;
	}



	public void seteMail(String eMail) {
		this.eMail = eMail;
	}



	public String getIme() {
		return ime;
	}



	public void setIme(String ime) {
		this.ime = ime;
	}



	public String getPrezime() {
		return prezime;
	}



	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}



	public String getAdresa() {
		return adresa;
	}



	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}



	public String getBrojTelefona() {
		return brojTelefona;
	}



	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}



	public Date getDatumRodjenja() {
		return datumRodjenja;
	}



	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}



	public LocalDateTime getDatumRegistracije() {
		return datumRegistracije;
	}



	public void setDatumRegistracije(LocalDateTime datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}



	public boolean isAdministrator() {
		return administrator;
	}



	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}



	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", eMail=" + eMail + ", ime=" + ime
				+ ", prezime=" + prezime + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona + ", datumRodjenja="
				+ datumRodjenja + ", datumRegistracije=" + datumRegistracije + ", administrator=" + administrator + "]";
	}
	
	
	
	
	
	


}
