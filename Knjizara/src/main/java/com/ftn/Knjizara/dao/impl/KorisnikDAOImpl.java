package com.ftn.Knjizara.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.model.Korisnik;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class KorisnikDAOImpl implements KorisnikDAO {
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class KorisnikRowMapper implements RowMapper<Korisnik> {

		@Override
		public Korisnik mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String korisnickoIme = rs.getString(index++);
			String lozinka = rs.getString(index++);
			String eMail = rs.getString(index++);
			String ime = rs.getString(index++);
			String prezime = rs.getString(index++);
			LocalDateTime datumRodj = rs.getTimestamp(index++).toLocalDateTime();
			LocalDateTime regDatumIVreme = rs.getTimestamp(index++).toLocalDateTime();
			String adresa = rs.getString(index++);
			String brTel = rs.getString(index++);
			Boolean administrator = rs.getBoolean(index++);

			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, ime, prezime, adresa, brTel, datumRodj, regDatumIVreme, administrator);
			return korisnik;
		}

	}

	
	
	
	

	@Override
	public Korisnik findOne(String korisnickoIme) {
		try {
			String sql = "SELECT * FROM korisnici WHERE korisnickoIme = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme);
		} catch (EmptyResultDataAccessException ex) {
			// ako korisnik nije pronađen
			return null;
		}
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		try {
			String sql = "SELECT * FROM korisnici WHERE korisnickoIme = ? AND lozinka = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme, lozinka);
		} catch (EmptyResultDataAccessException ex) {
			// ako korisnik nije pronađen
			return null;
		}
	}

	@Override
	public List<Korisnik> findAll() {
		String sql = "SELECT * FROM korisnici";
		return jdbcTemplate.query(sql, new KorisnikRowMapper());
	}

	@Override
	public List<Korisnik> find(String korisnickoIme, String eMail, String pol, Boolean administrator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Korisnik korisnik) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Korisnik korisnik) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String korisnickoIme) {
		// TODO Auto-generated method stub
		
	}

}
