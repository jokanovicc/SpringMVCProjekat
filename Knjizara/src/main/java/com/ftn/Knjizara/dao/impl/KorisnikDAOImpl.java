package com.ftn.Knjizara.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
			String eMail = rs.getString(index++);
			String ime = rs.getString(index++);
			String prezime = rs.getString(index++);
			Date datumRodj = rs.getDate(index++);
			LocalDateTime regDatumIVreme = rs.getTimestamp(index++).toLocalDateTime();
			String adresa = rs.getString(index++);
			String brTel = rs.getString(index++);
			Boolean administrator = rs.getBoolean(index++);
			Boolean blokiran = rs.getBoolean(index++);
			Boolean kartica = rs.getBoolean(index++);

			Korisnik korisnik = new Korisnik(korisnickoIme, null, eMail, ime, prezime, adresa, brTel, datumRodj, regDatumIVreme, administrator,blokiran,kartica);
			return korisnik;
		}

	}

	
	
	
	

	@Override
	public Korisnik findOne(String korisnickoIme) {
		try {
			String sql = "SELECT korisnickoIme,eMail,ime,prezime,datumRodjenja,vremeRegistracije,adresa,brojTelefona,administrator,blokiran,kartica FROM korisnici WHERE korisnickoIme = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme);
		} catch (EmptyResultDataAccessException ex) {
			// ako korisnik nije pronađen
			return null;
		}
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		try {
			String sql = "SELECT korisnickoIme,eMail,ime,prezime,datumRodjenja,vremeRegistracije,adresa,brojTelefona,administrator,blokiran,kartica FROM korisnici WHERE korisnickoIme = ? AND lozinka = ? and blokiran = 0";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme, lozinka);
		} catch (EmptyResultDataAccessException ex) {
			// ako korisnik nije pronađen
			return null;
		}
	}

	@Override
	public List<Korisnik> findAll() {
		String sql = "SELECT korisnickoIme,eMail,ime,prezime,datumRodjenja,vremeRegistracije,adresa,brojTelefona,administrator,blokiran,kartica FROM korisnici";
		return jdbcTemplate.query(sql, new KorisnikRowMapper());
	}



	@Override
	public void save(Korisnik korisnik) {
		String sql = "INSERT INTO korisnici (korisnickoIme, lozinka, eMail, ime,prezime,DatumRodjenja,adresa,brojTelefona,vremeRegistracije, administrator,blokiran,kartica) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?)";
		jdbcTemplate.update(sql, korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.geteMail(), korisnik.getIme(),korisnik.getPrezime(),korisnik.getDatumRodjenja(),korisnik.getAdresa(),korisnik.getBrojTelefona(),korisnik.getDatumRegistracije(),korisnik.isAdministrator(),korisnik.isBlokiran(),korisnik.isKartica());
		
	}

	@Override
	public void update(Korisnik korisnik) {
		if (korisnik.getLozinka() == null) {
			String sql = "UPDATE korisnici SET  administrator = ?, blokiran = ?,kartica = ? WHERE korisnickoIme = ?";
			jdbcTemplate.update(sql, korisnik.isAdministrator(),korisnik.isBlokiran(),korisnik.isKartica(), korisnik.getKorisnickoIme());
		} else {
			String sql = "UPDATE korisnici SET administrator = ?, blokiran = ?,kartica = ? WHERE korisnickoIme = ?";
			jdbcTemplate.update(sql, korisnik.isAdministrator(),korisnik.isBlokiran(),korisnik.isKartica(), korisnik.getKorisnickoIme());
		}
	}

	@Override
	public void delete(String korisnickoIme) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Korisnik> find(String korisnickoIme, String eMail, String ime, String prezime,
			String adresa, String brojTelefona, Date datumRodjenja, Boolean administrator,
			LocalDateTime datumRegistracije) {
		// TODO Auto-generated method stub
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT korisnickoIme,eMail,ime,prezime,datumRodjenja,vremeRegistracija,adresa,brojTelefona,administrator,blokiran,kartica FROM korisnici ";

		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(korisnickoIme!=null) {
			korisnickoIme = "%" + korisnickoIme + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("korisnickoIme LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(korisnickoIme);
		}
		
		
		if(eMail!=null) {
			eMail = "%" + eMail + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("eMail LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(eMail);
		}
		
		if(ime!=null) {
			ime = "%" + ime + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("ime LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(ime);
		}
		
		if(prezime!=null) {
			prezime = "%" + prezime + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("prezime LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(prezime);
		}
		
		if(datumRodjenja!=null) {
			imaArgumenata = true;
			listaArgumenata.add(datumRodjenja);
		}
		
		if(datumRegistracije!=null) {
			imaArgumenata = true;
			listaArgumenata.add(datumRegistracije);
		}
		
		
		
		if(adresa!=null) {
			adresa = "%" + adresa + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("adresa LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(adresa);
		}
		
		if(brojTelefona!=null) {
			brojTelefona = "%" + brojTelefona + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("brojTelefona LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(brojTelefona);
		}
		
		
		
		
		
		if(administrator!=null) {	
			//vraća samo administratore ili sve korisnike sistema
			String administratorSql = (administrator)? "administrator = 1": "administrator >= 0";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append(administratorSql);
			imaArgumenata = true;
		}
		
		
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY korisnickoIme";
		else
			sql=sql + " ORDER BY korisnickoIme";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new KorisnikRowMapper());

	}

}
