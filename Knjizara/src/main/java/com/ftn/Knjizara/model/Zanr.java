package com.ftn.Knjizara.model;

public class Zanr {
	
	private Long id;
	private String naziv;
	private String opis;
	
	
	public Zanr(Long id, String naziv, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
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


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	@Override
	public String toString() {
		return "Zanr [id=" + id + ", naziv=" + naziv + ", opis=" + opis + "]";
	}
	
	
	
	

}
