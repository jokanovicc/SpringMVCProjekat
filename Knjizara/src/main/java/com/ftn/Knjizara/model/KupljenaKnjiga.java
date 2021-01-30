package com.ftn.Knjizara.model;


public class KupljenaKnjiga {
	
	private Long id;
	private Knjiga knjiga;
	private int brojPrimeraka;
	private double cena;
	private Long kupovina;
	
	
	private Double sumaIzvestanje;
	private Integer brojKnjiga;
	private String naziv;
	private String autor;
	private Integer ukupnaKnjiga;
	private double ukupnaCena;
	
	
	public KupljenaKnjiga(Long id, Knjiga knjiga, int brojPrimeraka, double cena,Long kupovina) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.brojPrimeraka = brojPrimeraka;
		this.cena = cena;
		this.kupovina = kupovina;
	}



	public KupljenaKnjiga(Knjiga knjiga, Double sumaIzvestanje, Integer brojKnjiga, String naziv, String autor) {
		super();
		this.knjiga = knjiga;
		this.sumaIzvestanje = sumaIzvestanje;
		this.brojKnjiga = brojKnjiga;
		this.naziv = naziv;
		this.autor = autor;
	}






	public KupljenaKnjiga(Integer ukupnaKnjiga, double ukupnaCena) {
		super();
		this.ukupnaKnjiga = ukupnaKnjiga;
		this.ukupnaCena = ukupnaCena;
	}



	public Integer getUkupnaKnjiga() {
		return ukupnaKnjiga;
	}



	public void setUkupnaKnjiga(Integer ukupnaKnjiga) {
		this.ukupnaKnjiga = ukupnaKnjiga;
	}



	public double getUkupnaCena() {
		return ukupnaCena;
	}



	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	

	public KupljenaKnjiga(Long id, Knjiga knjiga, int brojPrimeraka, double cena, Long kupovina, Double sumaIzvestanje,
			Integer brojKnjiga, String naziv, String autor) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.brojPrimeraka = brojPrimeraka;
		this.cena = cena;
		this.kupovina = kupovina;
		this.sumaIzvestanje = sumaIzvestanje;
		this.brojKnjiga = brojKnjiga;
		this.naziv = naziv;
		this.autor = autor;
	}

	public Double getSumaIzvestanje() {
		return sumaIzvestanje;
	}

	public void setSumaIzvestanje(Double sumaIzvestanje) {
		this.sumaIzvestanje = sumaIzvestanje;
	}

	public Integer getBrojKnjiga() {
		return brojKnjiga;
	}

	public void setBrojKnjiga(Integer brojKnjiga) {
		this.brojKnjiga = brojKnjiga;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Long getKupovina() {
		return kupovina;
	}

	public void setKupovina(Long kupovina) {
		this.kupovina = kupovina;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KupljenaKnjiga other = (KupljenaKnjiga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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


	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}


	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	@Override
	public String toString() {
		return "KupljenaKnjiga [id=" + id + ", knjiga=" + knjiga + ", brojPrimeraka=" + brojPrimeraka + ", cena=" + cena
				+ "]";
	}
	
	
	
	
	

}
