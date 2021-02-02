package com.ftn.Knjizara.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.SpecijalniDatumDAO;
import com.ftn.Knjizara.model.SpecijalniDatum;


@Repository
public class SpecijalniDatumDaoImpl implements SpecijalniDatumDAO {
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private class SpecijalniDatumRowMapper implements RowMapper<SpecijalniDatum> {
		
		@Override
		public SpecijalniDatum mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
            Long id = rs.getLong(index++);
            Date datum = rs.getDate(index++);
            Integer popust = rs.getInt(index++);
            
            SpecijalniDatum specijalniDatum = new SpecijalniDatum(id, datum, popust);
            return specijalniDatum;

		}

	}
	
	

	

	@Override
	public SpecijalniDatum findOne(Long id) {
		String sql = 
				"SELECT l.id,l.datum,l.popust FROM specijalniDatum l "
				+" where l.id = ? "
				+ "ORDER BY l.id";
		return jdbcTemplate.queryForObject(sql, new SpecijalniDatumRowMapper(), id);
	}

	@Override
	public SpecijalniDatum nadji(Date date) {
		String sql = 
				"SELECT l.id,l.datum,l.popust FROM specijalniDatum l "
				+" where l.datum = ? ";
		return jdbcTemplate.queryForObject(sql, new SpecijalniDatumRowMapper(), date);
	}

	@Override
	public List<SpecijalniDatum> findAll() {
		String sql = 
				"SELECT l.id,l.datum,l.popust FROM specijalniDatum l "
				+ "ORDER BY l.id";
		return jdbcTemplate.query(sql,new SpecijalniDatumRowMapper());
	}

	@Override
	public int save(SpecijalniDatum specijalni) {
		String sql = "INSERT INTO specijalniDatum (datum, popust) VALUES (?,?)";
		return jdbcTemplate.update(sql,specijalni.getDatum(),specijalni.getPopust());
	}

}
