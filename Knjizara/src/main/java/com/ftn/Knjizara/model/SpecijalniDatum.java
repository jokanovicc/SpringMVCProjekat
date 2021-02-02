package com.ftn.Knjizara.model;

import java.sql.Date;

public class SpecijalniDatum {
	
	private Long id;
	private Date datum;
	private Integer popust;
	
	
	
	public SpecijalniDatum(Long id, Date datum, Integer popust) {
		super();
		this.id = id;
		this.datum = datum;
		this.popust = popust;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getDatum() {
		return datum;
	}



	public void setDatum(Date datum) {
		this.datum = datum;
	}



	public Integer getPopust() {
		return popust;
	}



	public void setPopust(Integer popust) {
		this.popust = popust;
	}



	@Override
	public String toString() {
		return "SpecijalniDatum [id=" + id + ", datum=" + datum + ", popust=" + popust + "]";
	}
	
	
	

}
