package com.ftn.Knjizara.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.dao.KupljenaKnjigaDAO;
import com.ftn.Knjizara.dao.KupovinaDAO;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;


@Repository
public class KupovinaDAOImpl implements KupovinaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private KupljenaKnjigaDAO kupljenaKnjigaDAO;
	

	
	private class KupovinaRowMapper implements RowMapper<Kupovina> {

		@Override
		public Kupovina mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long kupovinaId = rs.getLong(index++);
			Double ukupnaCena = rs.getDouble(index++);
			LocalDateTime DatumIVreme = rs.getTimestamp(index++).toLocalDateTime();
			String korisnickoIme = rs.getString(index++);
			Korisnik korisnik = korisnikDAO.findOne(korisnickoIme);
			List<KupljenaKnjiga> kupljeneKnjige = kupljenaKnjigaDAO.izvuciKupljeneKnjige(kupovinaId);
			
			Integer ukupanBrojKnjiga = rs.getInt(index++);

			
			Kupovina kupovine = new Kupovina(kupovinaId, ukupnaCena, DatumIVreme, korisnik, ukupanBrojKnjiga);
			return kupovine;
		}

	}
	
	
	
	
	
	
	
	

	@Override
	public Kupovina findOne(Long id) {
		String sql = 
				"SELECT k.id,k.ukupnaCena,k.datumKupovine,u.korisnickoIme,k.ukupanBrojKnjiga FROM kupovina k "
				+"LEFT JOIN korisnici u on u.korisnickoIme = k.korisnik " 
				+"WHERE k.id = ? " 
				+"ORDER BY k.id";
		return jdbcTemplate.queryForObject(sql, new KupovinaRowMapper(), id);
	}

	@Override
	public List<Kupovina> findAll() {
		String sql = 
				"SELECT k.id,k.ukupnaCena,k.datumKupovine,u.korisnickoIme,k.ukupanBrojKnjiga FROM kupovina k "
				+"LEFT JOIN korisnici u on u.korisnickoIme = k.korisnik " 
				+"ORDER BY k.id";
		return jdbcTemplate.query(sql, new KupovinaRowMapper());
	}
	
	
	@Override
	public List<Kupovina> findAllzaKorisnika(String korisnicko) {
		String sql = 
				"SELECT k.id,k.ukupnaCena,k.datumKupovine,u.korisnickoIme,k.ukupanBrojKnjiga FROM kupovina k "
				+"LEFT JOIN korisnici u on u.korisnickoIme = k.korisnik where k.korisnik = ? " 
				+"ORDER BY k.datumKupovine ASC";
		return jdbcTemplate.query(sql, new KupovinaRowMapper(),korisnicko);
	}
	
	

	@Override
	public int save(Kupovina kupovina) {
		String sql = "INSERT INTO kupovina (id,ukupnaCena,datumKupovine,korisnik,ukupanBrojKnjiga) VALUES (?,?, ?,?, ?)";
		return jdbcTemplate.update(sql,kupovina.getId(), kupovina.getUkupnaCena(),kupovina.getDatumKupovine(),kupovina.getKorisnik().getKorisnickoIme(),kupovina.getBrojKnjiga());
	}

	@Override
	public int update(Kupovina kupovina) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
