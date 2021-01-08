package com.ftn.Knjizara.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.KnjigaDAO;
import com.ftn.Knjizara.dao.ZanrDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;


@Repository
@Primary
public class KnjigaDAOImpl2 implements KnjigaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ZanrDAO zanrDAO;


	private class KnjigaRowMapper implements RowMapper<Knjiga> {

        private Map<Long, Knjiga> knjige = new LinkedHashMap<>();
       


		@Override
		public Knjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
        	System.out.println("USO");
            int index = 1;
            Long knjigaID = rs.getLong(index++);
            String naziv = rs.getString(index++);
            int isbn = rs.getInt(index++);
            String izdavac = rs.getString(index++);
            String autor = rs.getString(index++);
            int godinaIzdavanje = rs.getInt(index++);
            String opis = rs.getString(index++);
            Double cena = rs.getDouble(index++);
            int brojStrana = rs.getInt(index++);
            String jezik = rs.getString(index++);
            Double ocena = rs.getDouble(index++);
            String tipPoveza = rs.getString(index++);
            String pismo = rs.getString(index++);
            String slika = rs.getString(index++);

            Knjiga knjiga =  new Knjiga(knjigaID, naziv, isbn, izdavac, autor, godinaIzdavanje, slika, opis, cena, brojStrana, tipPoveza, pismo, jezik, ocena);
            return knjiga;
        }
    }

	@Override
	public Knjiga findOne(Long id) {
		String sql = "SELECT * FROM knjige WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new KnjigaRowMapper(), id);
	}

	@Override
	public List<Knjiga> findAll() {
		String sql = "SELECT * FROM knjige";
		return jdbcTemplate.query(sql, new KnjigaRowMapper());
	}



	@Override
	public int save(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Knjiga> find(String naziv) {
		naziv = "%" + naziv + "%";
		String sql = "SELECT * FROM knjige WHERE naziv LIKE ?";
		return jdbcTemplate.query(sql, new KnjigaRowMapper(), naziv);
	}

}
