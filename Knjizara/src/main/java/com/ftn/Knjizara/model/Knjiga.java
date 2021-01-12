package com.ftn.Knjizara.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class Knjiga {
	
	private Long id;
	private String naziv;
	private String ISBN;
	private String izdavackaKuca;
	private String autor;
	private int godinaIzdavanja;
	private String slikaKnjige;
	private String kratakOpis;
	private double cena;
	private int brojStrana;
	private String tipPoveza;
	private String pismo;
	private String jezik;
	private double ocena;
	private int kolicina;
	
	private List<Zanr> zanrovi = new ArrayList<>();
	

	






	public Knjiga(Long id, String naziv, String iSBN, String izdavackaKuca, String autor, int godinaIzdavanja,
			String slikaKnjige, String kratakOpis, double cena, int brojStrana, String tipPoveza, String pismo,
			String jezik, double ocena, int kolicina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ISBN = iSBN;
		this.izdavackaKuca = izdavackaKuca;
		this.autor = autor;
		this.godinaIzdavanja = godinaIzdavanja;
		this.slikaKnjige = slikaKnjige;
		this.kratakOpis = kratakOpis;
		this.cena = cena;
		this.brojStrana = brojStrana;
		this.tipPoveza = tipPoveza;
		this.pismo = pismo;
		this.jezik = jezik;
		this.ocena = ocena;
		this.kolicina = kolicina;
	}

	
	
	


	public Knjiga(String naziv, String iSBN, String izdavackaKuca, String autor, int godinaIzdavanja,
			String slikaKnjige, String kratakOpis, double cena, int brojStrana, String tipPoveza, String pismo,
			String jezik, double ocena, int kolicina) {
		super();
		this.naziv = naziv;
		this.ISBN = iSBN;
		this.izdavackaKuca = izdavackaKuca;
		this.autor = autor;
		this.godinaIzdavanja = godinaIzdavanja;
		this.slikaKnjige = slikaKnjige;
		this.kratakOpis = kratakOpis;
		this.cena = cena;
		this.brojStrana = brojStrana;
		this.tipPoveza = tipPoveza;
		this.pismo = pismo;
		this.jezik = jezik;
		this.ocena = ocena;
		this.kolicina = kolicina;
	}






	public int getKolicina() {
		return kolicina;
	}



	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}



	public String getKratakOpis() {
		return kratakOpis;
	}






	public void setKratakOpis(String kratakOpis) {
		this.kratakOpis = kratakOpis;
	}






	public String getIzdavackaKuca() {
		return izdavackaKuca;
	}






	public void setIzdavackaKuca(String izdavackaKuca) {
		this.izdavackaKuca = izdavackaKuca;
	}






	public Long getId() {
		return id;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(int godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public String getSlikaKnjige() {
		return slikaKnjige;
	}

	public void setSlikaKnjige(String slikaKnjige) {
		this.slikaKnjige = slikaKnjige;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getBrojStrana() {
		return brojStrana;
	}

	public void setBrojStrana(int brojStrana) {
		this.brojStrana = brojStrana;
	}

	public String getTipPoveza() {
		return tipPoveza;
	}

	public void setTipPoveza(String tipPoveza) {
		this.tipPoveza = tipPoveza;
	}

	public String getPismo() {
		return pismo;
	}

	public void setPismo(String pismo) {
		this.pismo = pismo;
	}

	public String getJezik() {
		return jezik;
	}

	public void setJezik(String jezik) {
		this.jezik = jezik;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	
	
	

	public List<Zanr> getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(List<Zanr> zanrovi) {
		this.zanrovi.clear();
		this.zanrovi.addAll(zanrovi);
	}

	@Override
	public String toString() {
		return "Knjiga [id=" + id + ", naziv=" + naziv + ", ISBN=" + ISBN + ", autor=" + autor + ", godinaIzdavanja="
				+ godinaIzdavanja + ", slikaKnjige=" + slikaKnjige + ", cena=" + cena + ", brojStrana=" + brojStrana
				+ ", tipPoveza=" + tipPoveza + ", pismo=" + pismo + ", jezik=" + jezik + ", ocena=" + ocena + "]";
	}
	

	
	
	

	

}
