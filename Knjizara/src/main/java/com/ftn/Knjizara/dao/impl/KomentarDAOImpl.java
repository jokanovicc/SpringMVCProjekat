package com.ftn.Knjizara.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.KnjigaDAO2;
import com.ftn.Knjizara.dao.KomentarDAO;
import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Komentar;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.LoyaltyKartica;
import com.ftn.Knjizara.model.StatusKomentara;

import ch.qos.logback.core.status.Status;

@Repository
public class KomentarDAOImpl implements KomentarDAO {
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private KnjigaDAO2 knjigaDAO;
	
	
	private class KomentarRowMapper implements RowMapper<Komentar> {
		
		@Override
		public Komentar mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
            Long komentarId = rs.getLong(index++);
            Integer ocena = rs.getInt(index++);
            Date datum = rs.getDate(index++);
            String korisnickoIme = rs.getString(index++);
            Korisnik korisnik = korisnikDAO.findOne(korisnickoIme);
            
            Long knjigaId = rs.getLong(index++);
            Knjiga knjiga = knjigaDAO.findOne(knjigaId);
            
            StatusKomentara status = StatusKomentara.valueOf(rs.getString(index++));
            String opis = rs.getString(index++);
          
            

            Komentar komentari = new Komentar(komentarId, opis, ocena, datum, korisnik, knjiga, status);
            return komentari;

		}

	}
	
	

	@Override
	public Komentar findOne(Long id) {
		String sql = 
				"SELECT l.id,l.ocena,l.datum, u.korisnickoIme, k.id, l.statusKomentara, l.opis  FROM komentar l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnik "
				+"LEFT JOIN knjige k on k.id = l.knjigaId " 
				+"WHERE l.id = ? " 
				+"ORDER BY l.id";
		return jdbcTemplate.queryForObject(sql, new KomentarRowMapper(), id);
	}

	@Override
	public List<Komentar> findAll() {
		String sql = 
				"SELECT l.id,l.ocena,l.datum, u.korisnickoIme, k.id, l.statusKomentara, l.opis  FROM komentar l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnik "
				+"LEFT JOIN knjige k  on k.id = l.knjigaId " 
				+"ORDER BY l.id";
		return jdbcTemplate.query(sql, new KomentarRowMapper());
	}

	@Override
	public Komentar izvuciOdKorisnika(String korisnicko) {
		String sql = 
				"SELECT l.id,l.ocena,l.datum, u.korisnickoIme, k.id, l.statusKomentara, l.opis  FROM komentar l "
				+"LEFT JOIN knjige k u on k.id = l.knjigaId  " 
				+"LEFT JOIN korisnici  on u.korisnickoIme = l.korisnicko " 					
				+"WHERE l.korisnicko = ? " 
				+"ORDER BY l.id";
		return jdbcTemplate.queryForObject(sql, new KomentarRowMapper(), korisnicko);
	}

	@Override
	public int save(Komentar komentar) {
		String sql = "INSERT INTO komentar (ocena,datum,korisnik,knjigaId,statusKomentara,opis) VALUES (?,?, ?,?,?,?)";
		return jdbcTemplate.update(sql,komentar.getOcena(),komentar.getVremeKomentara(),komentar.getKorisnik().getKorisnickoIme(),komentar.getKnjiga().getId(),komentar.getStatus().toString(),komentar.getTekstKomentara());
	}

	@Override
	public int update(Komentar komentar) {
		String sql = "UPDATE komentar SET ocena = ?, datum = ?, korisnik = ?,knjigaId = ?,statusKomentara = ?,opis = ? where id = ? ";
		return jdbcTemplate.update(sql,komentar.getOcena(),komentar.getVremeKomentara(),komentar.getKorisnik().getKorisnickoIme(),komentar.getKnjiga().getId(),komentar.getStatus().toString(),komentar.getTekstKomentara(),komentar.getId());
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
