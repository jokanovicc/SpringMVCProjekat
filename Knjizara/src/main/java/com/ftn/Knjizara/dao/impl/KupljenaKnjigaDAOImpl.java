package com.ftn.Knjizara.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ftn.Knjizara.dao.KnjigaDAO2;
import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.dao.KupljenaKnjigaDAO;
import com.ftn.Knjizara.dao.KupovinaDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;

public class KupljenaKnjigaDAOImpl implements KupljenaKnjigaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private KnjigaDAO2 knjigaDAO;
	
	@Autowired
	private KupovinaDAO kupovinaDAO;
	
	
	
	private class KupljenaKnjigaRowMapper implements RowMapper<KupljenaKnjiga> {

		@Override
		public KupljenaKnjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long kupljenaKnjigaId = rs.getLong(index++);
			
			Long knjigaId = rs.getLong(index++);
			
			Knjiga knjiga = knjigaDAO.findOne(knjigaId);
			
			Long kupovinaId = rs.getLong(index++);
			Kupovina kupovina = kupovinaDAO.findOne(kupovinaId);
			
			int brojPrimeraka = rs.getInt(index++);
			Double cena = rs.getDouble(index++);
			
			KupljenaKnjiga kupljenaKnjiga = new KupljenaKnjiga(kupljenaKnjigaId, knjiga, brojPrimeraka, cena,kupovina);
			return kupljenaKnjiga;
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
		String sql = "INSERT INTO kupovina (knjigaID, kupovinaId, brojPrimeraka, cena) VALUES (?, ?)";
		return jdbcTemplate.update(sql, kupljenaKnjiga.getKnjiga().getId(),kupljenaKnjiga.getKupovina().getId(),kupljenaKnjiga.getBrojPrimeraka(),kupljenaKnjiga.getCena());
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
				"SELECT * FROM kupljenaKnjiga where kupovinaId = ? ";
//				"SELECT k.id,kk.id, u.id, k.brojPrimeraka,k.cena  FROM kupljenaKnjiga k "
//				+"LEFT JOIN knjige kk ON kk.id = k.knjigaID "
//				+"LEFT JOIN kupovina u on u.id = k.kupovinaId WHERE u.id = ? " 
//				+"ORDER BY k.id";
		return jdbcTemplate.query(sql, new KupljenaKnjigaRowMapper(),id);
	}
	

}
