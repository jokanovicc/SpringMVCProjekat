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
import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.dao.KupljenaKnjigaDAO;
import com.ftn.Knjizara.dao.KupovinaDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;


@Repository
public class KupljenaKnjigaDAOImpl implements KupljenaKnjigaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private KnjigaDAO2 knjigaDAO;
	
	
	
	
	private class KupljenaKnjigaRowMapper implements RowMapper<KupljenaKnjiga> {

		@Override
		public KupljenaKnjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long kupljenaKnjigaId = rs.getLong(index++);
			
			Long knjigaId = rs.getLong(index++);
			
			Knjiga knjiga = knjigaDAO.findOne(knjigaId);
			
			Long kupovinaId = rs.getLong(index++);
			
			int brojPrimeraka = rs.getInt(index++);
			Double cena = rs.getDouble(index++);
			
			KupljenaKnjiga kupljenaKnjiga = new KupljenaKnjiga(kupljenaKnjigaId, knjiga, brojPrimeraka, cena,kupovinaId);
			return kupljenaKnjiga;
		}

	}
	
	private class IzvestavanjeRowMapper implements RowMapper<KupljenaKnjiga> {

		@Override
		public KupljenaKnjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Integer ukupnoKnjiga = rs.getInt(index++);
			Double ukupnaCena = rs.getDouble(index++);
			
			KupljenaKnjiga kupljenaKnjiga1 = new KupljenaKnjiga(ukupnoKnjiga, ukupnaCena);
			return kupljenaKnjiga1;
		}

	}

	private class IzvestavanjeRowMapper2 implements RowMapper<KupljenaKnjiga> {

		@Override
		public KupljenaKnjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long knjigaId = rs.getLong(index++);
			Knjiga knjiga = knjigaDAO.findOne(knjigaId);
			String naziv = rs.getString(index++);
			String autor = rs.getString(index++);
			Integer sumaKnjiga = rs.getInt(index++);
			Double sumaCena = rs.getDouble(index++);
			
			
			KupljenaKnjiga kupljenaKnjiga2 = new KupljenaKnjiga(knjiga, sumaCena, sumaKnjiga, naziv, autor);
			return kupljenaKnjiga2;
		}

	}

	
	
	
	@Override
	public KupljenaKnjiga findOne(Long id) {
		String sql = 
				"SELECT k.id,kk.id, u.id, k.brojPrimeraka,k.cena  FROM kupljenaKnjiga k "
				+"LEFT JOIN knjige kk ON kk.id = k.knjigaID "
				+"LEFT JOIN kupovina u on u.id = k.kupovinaId " 
				+"WHERE k.id = ? " 
				+"ORDER BY k.id";
		return jdbcTemplate.queryForObject(sql, new KupljenaKnjigaRowMapper(), id);
	}

	@Override
	public List<KupljenaKnjiga> findAll() {
		String sql = 
				"SELECT k.id,kk.id, u.id, k.brojPrimeraka,k.cena  FROM kupljenaKnjiga k "
				+"LEFT JOIN knjige kk ON kk.id = k.knjigaID "
				+"LEFT JOIN kupovina u on u.id = k.kupovinaId " 
				+"ORDER BY k.id";
		return jdbcTemplate.query(sql, new KupljenaKnjigaRowMapper());
	}

	@Override
	public int save(KupljenaKnjiga kupljenaKnjiga) {
		String sql = "INSERT INTO kupljenaKnjiga (knjigaID, kupovinaId, brojPrimeraka, cena) VALUES (?, ?,?,?)";
		return jdbcTemplate.update(sql, kupljenaKnjiga.getKnjiga().getId(),kupljenaKnjiga.getKupovina(),kupljenaKnjiga.getBrojPrimeraka(),kupljenaKnjiga.getCena());
	}

	@Override
	public int update(KupljenaKnjiga kupljenaKnjiga) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KupljenaKnjiga> izvuciKupljeneKnjige(Long id) {
		String sql = 
				"SELECT k.id,kk.id, u.id, k.brojPrimeraka,k.cena  FROM kupljenaKnjiga k "
				+"LEFT JOIN knjige kk ON kk.id = k.knjigaID "
				+"LEFT JOIN kupovina u on u.id = k.kupovinaId where u.id = ? " 
				+"ORDER BY k.id";
		return jdbcTemplate.query(sql, new KupljenaKnjigaRowMapper(),id);
	}

	@Override
	public KupljenaKnjiga izvuciIzvestavanja(Date datum1, Date datum2) {
		String sql = 
				" SELECT count(k.naziv),sum(k.cena) FROM kupljenaKnjiga kg "
				+ " LEFT JOIN knjige k ON k.id = kg.knjigaId "
				+ "	LEFT JOIN kupovina kp on kp.id = kg.kupovinaId where kp.datumKupovine > ?"
				+ "	AND kp.datumKupovine < ? ";
		return jdbcTemplate.queryForObject(sql, new IzvestavanjeRowMapper(), datum1,datum2);
	}
	
	
	
	@Override
	public List<KupljenaKnjiga> izvuciIzvestavanja2(Date datum1, Date datum2) {
		String sql = 
				"SELECT k.id, k.naziv,k.autor,count(k.naziv),sum(k.cena) FROM kupljenaKnjiga kg "
				+"LEFT JOIN knjige k ON k.id = kg.knjigaId "
				+"LEFT JOIN kupovina kp on kp.id = kg.kupovinaId where kp.datumKupovine > ? " 
				+" AND kp.datumKupovine < ? "
				+"GROUP BY k.id,k.naziv,k.autor";
		return jdbcTemplate.query(sql, new IzvestavanjeRowMapper2(),datum1,datum2);
	}
	

}
