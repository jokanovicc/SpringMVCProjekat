package com.ftn.Knjizara.model;


public class KupljenaKnjiga {
	
	private Long id;
	private Knjiga knjiga;
	private int brojPrimeraka;
	private double cena;
	private Kupovina kupovina;
	
	
	public KupljenaKnjiga(Long id, Knjiga knjiga, int brojPrimeraka, double cena,Kupovina kupovina) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.brojPrimeraka = brojPrimeraka;
		this.cena = cena;
		this.kupovina = kupovina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	


	public Kupovina getKupovina() {
		return kupovina;
	}

	public void setKupovina(Kupovina kupovina) {
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
