package com.ftn.Knjizara.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.dao.LoyaltyKarticaDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.Kupovina;
import com.ftn.Knjizara.model.LoyaltyKartica;
import com.ftn.Knjizara.model.Zanr;

@Repository
public class LoyaltyKarticaDAOImpl implements LoyaltyKarticaDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	
	
	
	private class LoyaltyKarticaRowMapper implements RowMapper<LoyaltyKartica> {
		
		@Override
		public LoyaltyKartica mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
            Long loyaltyId = rs.getLong(index++);
            Double popust = rs.getDouble(index++);
            Integer brojPoena = rs.getInt(index++);
            String korisnickoIme = rs.getString(index++);
            Korisnik korisnik = korisnikDAO.findOne(korisnickoIme);
            Boolean odobrena = rs.getBoolean(index++);
            
            
            LoyaltyKartica loyaltyKartice = new LoyaltyKartica(loyaltyId, korisnik, popust, brojPoena,odobrena);
            return loyaltyKartice;

		}

	}
	
	

	@Override
	public LoyaltyKartica findOne(Long id) {
		String sql = 
				"SELECT l.id,l.popust,l.brojPoena,u.korisnickoIme,l.odobrena FROM loyaltykartica l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnicko " 
				+"WHERE l.id = ? " 
				+"ORDER BY l.id";
		return jdbcTemplate.queryForObject(sql, new LoyaltyKarticaRowMapper(), id);
	}

	@Override
	public List<LoyaltyKartica> findAll() {
		String sql = 
				"SELECT l.id,l.popust,l.brojPoena,u.korisnickoIme,l.odobrena FROM loyaltykartica l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnicko" ;
		return jdbcTemplate.query(sql, new LoyaltyKarticaRowMapper());
	}

	@Override
	public LoyaltyKartica izvuciKorisnikovu(String korisnicko) {
		String sql = 
				"SELECT l.id,l.popust,l.brojPoena,u.korisnickoIme,l.odobrena FROM loyaltykartica l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnicko " 
				+"WHERE l.korisnicko = ? " 
				+"ORDER BY l.id";
		return jdbcTemplate.queryForObject(sql, new LoyaltyKarticaRowMapper(), korisnicko);
	}

	@Override
	public int save(LoyaltyKartica loyaltyKartica) {
		String sql = "INSERT INTO loyaltyKartica (popust,brojPoena,korisnicko,odobrena) VALUES (?,?, ?,?)";
		return jdbcTemplate.update(sql,loyaltyKartica.getPopust(),loyaltyKartica.getBrojPoena(),loyaltyKartica.getKorisnik().getKorisnickoIme(),loyaltyKartica.isOdobrena());
	}
	

	@Override
	public int update(LoyaltyKartica loyaltyKartica) {
		String sql = "UPDATE loyaltyKartica SET popust = ?, brojPoena = ?, odobrena = ? where korisnicko = ? ";
		return jdbcTemplate.update(sql,loyaltyKartica.getPopust(),loyaltyKartica.getBrojPoena(),loyaltyKartica.isOdobrena(),loyaltyKartica.getKorisnik().getKorisnickoIme());
	}

	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM loyaltyKartica WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
