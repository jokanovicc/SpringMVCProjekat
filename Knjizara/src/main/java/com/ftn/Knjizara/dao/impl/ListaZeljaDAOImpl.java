package com.ftn.Knjizara.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.KnjigaDAO2;
import com.ftn.Knjizara.dao.KorisnikDAO;
import com.ftn.Knjizara.dao.ListaZeljaDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.ListaZelja;


@Repository
public class ListaZeljaDAOImpl implements ListaZeljaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	@Autowired
	private KnjigaDAO2 knjigaDAO;
	
	private class ListaZeljaRowMapper implements RowMapper<ListaZelja> {
		
		@Override
		public ListaZelja mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
            Long id = rs.getLong(index++);
            String korisnickoIme = rs.getString(index++);
            Korisnik korisnik = korisnikDAO.findOne(korisnickoIme);
            Long knjigaId = rs.getLong(index++);
            Knjiga knjiga = knjigaDAO.findOne(knjigaId);
            
            ListaZelja listaZelja = new ListaZelja(id, knjiga, korisnik);
            return listaZelja;

		}

	}
	
	
	
	@Override
	public ListaZelja findOne(Long id) {
		String sql = 
				"SELECT l.id,u.korisnickoIme, k.id FROM listazelja l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnik " 
				+"LEFT JOIN knjige k on k.id = l.knjigaId " 
				+"WHERE l.id = ? " 
				+"ORDER BY l.id";
		return jdbcTemplate.queryForObject(sql, new ListaZeljaRowMapper(), id);
	}

	@Override
	public List<ListaZelja> findAll() {
		String sql = 
				"SELECT l.id,u.korisnickoIme, k.id FROM listazelja l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnicko " 
				+"LEFT JOIN knjige k u on k.id = l.knjigaId " 
				+"ORDER BY l.id";
		return jdbcTemplate.query(sql, new ListaZeljaRowMapper());
	}

	@Override
	public List<ListaZelja> izvuciKorisnikove(String korisnicko) {
		String sql = 
				"SELECT l.id,u.korisnickoIme, k.id FROM listazelja l "
				+"LEFT JOIN korisnici u on u.korisnickoIme = l.korisnik " 
				+"LEFT JOIN knjige k on k.id = l.knjigaId "
				+" WHERE u.korisnickoIme = ? "; 
		return jdbcTemplate.query(sql, new ListaZeljaRowMapper(),korisnicko);
	}

	@Override
	public int save(ListaZelja listaZelja) {
		String sql = "INSERT INTO listazelja (korisnik,knjigaId) VALUES (?,?)";
		return jdbcTemplate.update(sql,listaZelja.getKorisnik().getKorisnickoIme(),listaZelja.getKnjiga().getId());
	}

	@Override
	public int update(ListaZelja listaZelja) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM listazelja WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
